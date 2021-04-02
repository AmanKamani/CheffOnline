package jb.svb.cheffonline.data

import android.util.Patterns

data class RegistrationForm(
    var name: String="",
    var email: String="",
    var mobile: String="",
    var password: String=""
){

    private var errorMsg = ""

    fun validate():Boolean{
        if(name.isEmpty() ||validateEmail() || validateMobile() || password.isEmpty()){
            errorMsg = "All fields are mandatory"
            return false
        }
        return true
    }

    fun validateEmail(): Boolean{
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            errorMsg = "Enter Valid Email ID"
            return false
        }
        return true
    }

    fun validateMobile(): Boolean{
        val mobileStr = mobile.toString()
        if(mobileStr.isEmpty() || !Patterns.PHONE.matcher(mobileStr).matches()) {
            errorMsg = "Enter Valid Mobile Number"
            return false
        }
        return true
    }

    fun getErrorMsg() = errorMsg
}