package com.autumnsun.suncoinmarket.features.feature_favorite.domain.repository

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getAllFavoriteCoins(): Flow<Resource<List<FavoriteCoinModel>>>
}