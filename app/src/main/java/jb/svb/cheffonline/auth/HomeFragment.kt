package jb.svb.cheffonline.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import jb.svb.cheffonline.R
import jb.svb.cheffonline.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.signUpButton.setOnClickListener {
            goToSignUp()
        }

        binding.signInButton.setOnClickListener{
            goToSignIn()
        }
    }

    private fun goToSignUp() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSignUpFragment())
    }

    private fun goToSignIn(){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSignInFragment())
    }
}