package jb.svb.cheffonline.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import jb.svb.cheffonline.R
import jb.svb.cheffonline.data.RegistrationForm
import jb.svb.cheffonline.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private var registrationForm = RegistrationForm()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        binding.emailEditText.addTextChangedListener {
            registrationForm.email = it.toString()
            if (!registrationForm.validateEmail()) {
                binding.emailEditText.error = registrationForm.getErrorMsg()
            }
        }

        binding.phoneEditText.addTextChangedListener {
            registrationForm.mobile = it.toString()
            if (!registrationForm.validateMobile()) {
                binding.phoneEditText.error = registrationForm.getErrorMsg()
            }
        }

        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val mobile = binding.phoneEditText.text.toString().trim()

            registrationForm = RegistrationForm(
                name,
                email,
                mobile,
                password
            )

            if (registrationForm.validate()) {
                Snackbar.make(view, "Success", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(view, registrationForm.getErrorMsg(), Snackbar.LENGTH_LONG).show()
            }
        }
    }

}