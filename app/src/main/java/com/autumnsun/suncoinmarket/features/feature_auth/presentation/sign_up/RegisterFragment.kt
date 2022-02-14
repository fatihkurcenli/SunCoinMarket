package com.autumnsun.suncoinmarket.features.feature_auth.presentation.sign_up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
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
        binding.btnRegister.setOnClickListener {
            viewModel.register(binding.edtMail.text.toString(), binding.edtPassword.text.toString())
        }

        lifecycleScope.launch {
            viewModel.registerState.collectLatest { state ->
                if (state.isLoading) {
                    Timber.d("Tag", "loading")
                } else {
                    if (state.successMessage.isNotBlank()) {
                        showSnackBar(state.successMessage)
                    }
                    if (state.errorMessage.isNotBlank()) {
                        showSnackBar(state.errorMessage)
                    }
                }
            }

        }
    }
}