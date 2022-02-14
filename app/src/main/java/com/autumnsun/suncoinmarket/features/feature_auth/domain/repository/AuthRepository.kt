package com.autumnsun.suncoinmarket.features.feature_auth.domain.repository

import com.autumnsun.suncoinmarket.core.util.Resource

interface AuthRepository {
    suspend fun loginEmail(email: String, password: String): Resource<Boolean>
    suspend fun registerEmail(email: String, password: String, userName: String): Resource<Boolean>
    fun isUserAuthenticatedInFirebase(): Boolean
}