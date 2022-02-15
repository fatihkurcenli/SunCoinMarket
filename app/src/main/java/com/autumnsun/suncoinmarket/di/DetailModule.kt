package com.autumnsun.suncoinmarket.di

import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_detail.data.DetailRepositoryImp
import com.autumnsun.suncoinmarket.features.feature_detail.domain.repository.DetailRepository
import com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases.GetCoinUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DetailModule {

    @Provides
    @Singleton
    fun provideDetailRepository(
        apiService: CryptoApi
    ): DetailRepository {
        return DetailRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(
        detailRepository: DetailRepository
    ): GetCoinUseCase {
        return GetCoinUseCase(detailRepository)
    }
}