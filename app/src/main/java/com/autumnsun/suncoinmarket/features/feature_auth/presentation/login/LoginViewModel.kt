package com.autumnsun.suncoinmarket.features.feature_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState>
        get() = _loginState


    fun loginEmail(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        authUseCases.loginUseCase(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _loginState.value = loginState.value.copy(
                        isLoading = false,
                        successMessage = result.data!!,
                        errorMessage = ""
                    )
                }
                is Resource.Loading -> {
                    _loginState.value = loginState.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _loginState.value = loginState.value.copy(
                        isLoading = false,
                        errorMessage = result.message!!
                    )
                }
            }
        }.launchIn(this)
    }
}