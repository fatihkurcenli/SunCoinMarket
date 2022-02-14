package com.autumnsun.suncoinmarket.features.feature_auth.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterUiState())
    val registerState: StateFlow<RegisterUiState>
        get() = _registerState


    fun register(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        authUseCases.registerUseCase(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _registerState.value = registerState.value.copy(
                        isLoading = false,
                        successMessage = result.data!!,
                        errorMessage = ""
                    )
                }
                is Resource.Loading -> {
                    _registerState.value = registerState.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _registerState.value = registerState.value.copy(
                        isLoading = false,
                        errorMessage = result.message!!
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}