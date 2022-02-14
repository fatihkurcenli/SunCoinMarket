package com.autumnsun.suncoinmarket.features.feature_auth.presentation.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val successMessage: String = ""
)