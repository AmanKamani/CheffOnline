package jb.svb.cheffonline.ui.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import jb.svb.cheffonline.R
import jb.svb.cheffonline.databinding.FragmentSignInBinding
import jb.svb.cheffonline.utils.showSnackBar
import jb.svb.cheffonline.viewmodels.SignInViewModel
import jb.svb.cheffonline.viewmodels.SignInViewModelFactory

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var signInViewModel: SignInViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        val factory = SignInViewModelFactory(requireActivity().application)
        signInViewModel = ViewModelProvider(this, factory).get(SignInViewModel::class.java)

        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = signInViewModel

        setEventListeners()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        signInViewModel.isFormValid.observe(this.viewLifecycleOwner){
            binding.loginButton.isEnabled = it
        }
    }

    private fun setEventListeners() {
        binding.emailEditText.addTextChangedListener {
            signInViewModel.validateEmail()
        }

        binding.passwordEditText.addTextChangedListener {
            signInViewModel.validatePassword()
        }

        binding.loginButton.setOnClickListener {
            signInViewModel.login()
        }
    }

}