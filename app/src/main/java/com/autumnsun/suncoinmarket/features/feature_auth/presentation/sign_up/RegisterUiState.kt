package com.autumnsun.suncoinmarket.features.feature_auth.presentation.sign_up

data class RegisterUiState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val successMessage: String = ""
)