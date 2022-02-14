package com.autumnsun.suncoinmarket.features.feature_auth.domain.repository

import com.autumnsun.suncoinmarket.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginEmail(email: String, password: String): Resource<Boolean>
    suspend fun registerEmail(email: String, password: String): Resource<Boolean>
    fun isUserAuthenticatedInFirebase(): Boolean
}