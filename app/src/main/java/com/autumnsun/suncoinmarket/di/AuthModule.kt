package com.autumnsun.suncoinmarket.di

import com.autumnsun.suncoinmarket.features.feature_auth.data.AuthRepositoryImp
import com.autumnsun.suncoinmarket.features.feature_auth.domain.repository.AuthRepository
import com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case.AuthUseCases
import com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case.LoginUseCase
import com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImp(
            firebaseAuth = FirebaseAuth.getInstance()
        )
    }


    @Provides
    @Singleton
    fun provideAuthUseCases(
        authRepository: AuthRepository
    ): AuthUseCases {
        return AuthUseCases(
            loginUseCase = LoginUseCase(authRepository),
            registerUseCase = RegisterUseCase(authRepository)
        )
    }

}