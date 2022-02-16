package com.autumnsun.suncoinmarket.di

import com.autumnsun.suncoinmarket.data.local.dao.CoinDao
import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_detail.data.DetailRepositoryImp
import com.autumnsun.suncoinmarket.features.feature_detail.domain.repository.DetailRepository
import com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases.DetailUseCases
import com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases.FavoriteUseCase
import com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases.GetCoinUseCase
import com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases.IsFavoriteUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
        apiService: CryptoApi,
        firebaseDb: FirebaseFirestore,
        firebaseAuth: FirebaseAuth,
        localDb: CoinDao
    ): DetailRepository {
        return DetailRepositoryImp(apiService, firebaseDb, firebaseAuth, localDb)
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(
        detailRepository: DetailRepository
    ): DetailUseCases {
        return DetailUseCases(
            getCoinUseCase = GetCoinUseCase(detailRepository),
            favoriteUseCase = FavoriteUseCase(detailRepository),
            isFavoriteUseCase = IsFavoriteUseCase(detailRepository)
        )
    }
}