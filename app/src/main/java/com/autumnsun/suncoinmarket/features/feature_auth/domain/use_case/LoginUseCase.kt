package com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case

import com.autumnsun.suncoinmarket.features.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase(
    private val authRepository: AuthRepository
) {
}