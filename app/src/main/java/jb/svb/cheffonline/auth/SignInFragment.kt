package jb.svb.cheffonline.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import jb.svb.cheffonline.R
import jb.svb.cheffonline.data.LoginForm
import jb.svb.cheffonline.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private var loginForm = LoginForm()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        binding.emailEditText.addTextChangedListener {
            loginForm.email = it.toString()
            if (!loginForm.validateEmail()) {
                binding.emailEditText.error = loginForm.getErrorMessage()
            }
        }

        binding.passwordEditText.addTextChangedListener {
            loginForm.password = it.toString()
            if (!loginForm.validatePassword()) {
                binding.passwordEditText.error = loginForm.getErrorMessage()
            }
        }

        binding.loginButton.setOnClickListener {
            loginForm =
                LoginForm(binding.emailEditText.toString(), binding.passwordEditText.toString())
            if (!loginForm.validateLoginForm()) {
                Snackbar.make(view, loginForm.getErrorMessage(), Snackbar.LENGTH_LONG).show()
            }
            else {
                Snackbar.make(view, getString(R.string.login_success_snackbar_text), Snackbar.LENGTH_LONG).show()
            }
        }
    }

}