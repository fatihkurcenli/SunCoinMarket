package com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String, userName: String): Flow<Resource<String>> =
        flow {
            if (password.length <= 6) {
                emit(Resource.Error(message = "Password can not be less than 6 charachter"))
                return@flow
            }
            emit(Resource.Loading())
            val response = authRepository.registerEmail(email, password, userName)
            if (response.data != null && response.data) {
                emit(Resource.Success(data = "Register Success"))
            }
            response.message?.let {
                if (response.message.isNotBlank()) {
                    emit(Resource.Error("${response.message}"))
                }
            }
        }
}