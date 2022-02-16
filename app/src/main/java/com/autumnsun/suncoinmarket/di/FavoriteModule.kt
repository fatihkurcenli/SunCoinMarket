package com.autumnsun.suncoinmarket.di

import com.autumnsun.suncoinmarket.features.feature_favorite.data.FavoriteRepositoryImpl
import com.autumnsun.suncoinmarket.features.feature_favorite.domain.repository.FavoriteRepository
import com.autumnsun.suncoinmarket.features.feature_favorite.domain.use_cases.GetAllFavoriteUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        firebaseAuth: FirebaseAuth,
        firebaseDb: FirebaseFirestore
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(firebaseAuth, firebaseDb)
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCases(
        favoriteRepository: FavoriteRepository
    ): GetAllFavoriteUseCase = GetAllFavoriteUseCase(favoriteRepository)
}