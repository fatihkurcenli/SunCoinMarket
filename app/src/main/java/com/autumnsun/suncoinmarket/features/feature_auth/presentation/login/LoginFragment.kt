package com.autumnsun.suncoinmarket.features.feature_auth.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
import com.autumnsun.suncoinmarket.core.presentation.MainActivity
import com.autumnsun.suncoinmarket.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            viewModel.loginEmail(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }

        lifecycleScope.launch {
            viewModel.loginState.collectLatest { state ->
                if (state.isLoading) {
                    binding.loginButton.isVisible = false
                    binding.loadingProgressBar.customLoadingAnimation.isVisible = true
                } else {
                    if (state.successMessage.isNotBlank()) {
                        showSnackBar(state.successMessage)
                        Intent(requireActivity(), MainActivity::class.java).apply {
                            startActivity(this)
                        }
                        binding.loginButton.isVisible = true
                        binding.loadingProgressBar.customLoadingAnimation.isVisible = false
                    }
                    if (state.errorMessage.isNotBlank()) {
                        showSnackBar(state.errorMessage)
                        binding.loadingProgressBar.customLoadingAnimation.isVisible = false
                        binding.loginButton.isVisible = true
                    }
                }
            }
        }
    }
}