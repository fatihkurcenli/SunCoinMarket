package com.autumnsun.suncoinmarket.di

import android.content.SharedPreferences
import com.autumnsun.suncoinmarket.data.local.dao.CoinDao
import com.autumnsun.suncoinmarket.features.feature_auth.data.AuthRepositoryImp
import com.autumnsun.suncoinmarket.features.feature_auth.domain.repository.AuthRepository
import com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case.AuthUseCases
import com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case.LoginUseCase
import com.autumnsun.suncoinmarket.features.feature_auth.domain.use_case.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firebaseDb: FirebaseFirestore,
        sharedPreferences: SharedPreferences,
        localDb: CoinDao
    ): AuthRepository {
        return AuthRepositoryImp(
            firebaseAuth = firebaseAuth,
            firebaseDb = firebaseDb,
            sharedPreferences = sharedPreferences,
            localDb = localDb
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