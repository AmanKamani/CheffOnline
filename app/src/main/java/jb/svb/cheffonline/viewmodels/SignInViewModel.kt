package jb.svb.cheffonline.viewmodels

import android.app.Application
import android.net.DnsResolver
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jb.svb.cheffonline.R
import jb.svb.cheffonline.data.LoginForm
import jb.svb.cheffonline.network.NetworkServices
import jb.svb.cheffonline.network.requests.LoginNetworkRequest
import jb.svb.cheffonline.network.responses.LoginNetworkResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(application: Application) : AndroidViewModel(application), Observable {

    private val loginForm = LoginForm()
    private val authApi = NetworkServices().getAuthApi()

    @Bindable
    var emailText: String = ""
    var emailError = MutableLiveData<String>()

    @Bindable
    var passwordText: String = ""
    var passwordError = MutableLiveData<String>()

    val isFormValid = MutableLiveData<Boolean>(false)


    fun validateEmail() {
        loginForm.email = emailText
        emailError.value = if (!loginForm.validateEmail()) {
            isFormValid.value = false
            getString()
        }
        else {
            validateForm()
            ""
        }
    }

    fun validatePassword() {
        loginForm.password = passwordText
        passwordError.value = if (!loginForm.validatePassword()) {
            isFormValid.value = false
            getString()
        }
        else{
            validateForm()
            ""
        }
    }

    fun validateForm() {
        isFormValid.value = loginForm.validateLoginForm()
    }

    private fun getString(): String =
        getApplication<Application>().resources.getString(loginForm.getErrorMessage())

    fun login() {
        val login = LoginNetworkRequest(emailText, passwordText)

        authApi.login(login).enqueue(object: Callback<LoginNetworkResponse>{
            override fun onResponse(
                call: Call<LoginNetworkResponse>,
                response: Response<LoginNetworkResponse>
            ) {
                Log.e("$$$","Response: ${response.body()}")
            }

            override fun onFailure(call: Call<LoginNetworkResponse>, t: Throwable) {
                Log.e("$$$","Failure : ${t.message}")
            }

        })
    }


    /**
     * Observable Implementation
     */
    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.remove(callback)
    }
}


class SignInViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(application) as T
        } else
            throw IllegalArgumentException("Invalid Argument")
    }

}