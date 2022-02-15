package com.autumnsun.suncoinmarket.di

import android.content.Context
import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_home.data.HomeRepositoryImp
import com.autumnsun.suncoinmarket.features.feature_home.domain.repository.HomeRepository
import com.autumnsun.suncoinmarket.features.feature_home.domain.use_cases.GetAllCoins
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        apiService: CryptoApi,
        context: Context
    ): HomeRepository {
        return HomeRepositoryImp(apiService, context)
    }

    @Provides
    @Singleton
    fun provideHomeUseCases(
        homeRepository: HomeRepository
    ): GetAllCoins {
        return GetAllCoins(
            homeRepository
        )
    }
}