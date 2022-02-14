package com.autumnsun.suncoinmarket.features.feature_auth.data

data class UserModel(
    val userName: String,
    val userId: String,
    val email: String,
    val password: String,
    val token: String
)
