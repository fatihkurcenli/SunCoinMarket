package com.autumnsun.suncoinmarket.features.feature_auth.presentation.sign_up

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
import com.autumnsun.suncoinmarket.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            viewModel.register(
                binding.edtMail.text.toString(),
                binding.edtPassword.text.toString(),
                binding.userName.text.toString()
            )
        }

        binding.loginButton.setOnClickListener {
            findNavController().navigateUp()
        }

        lifecycleScope.launch {
            viewModel.registerState.collectLatest { state ->
                if (state.isLoading) {
                    binding.registerButton.isVisible = false
                    Timber.d("Tag", "loading")
                    binding.loadingProgressBar.customLoadingAnimation.isVisible = true
                } else {
                    if (state.successMessage.isNotBlank()) {
                        showSnackBar(state.successMessage)
                        Intent(requireActivity(), MainActivity::class.java).apply {
                            startActivity(this)
                        }
                        binding.registerButton.isVisible = true
                        binding.loadingProgressBar.customLoadingAnimation.isVisible = false
                    }
                    if (state.errorMessage.isNotBlank()) {
                        showSnackBar(state.errorMessage)
                        binding.loadingProgressBar.customLoadingAnimation.isVisible = false
                        binding.registerButton.isVisible = true
                    }
                }
            }
        }
    }
}