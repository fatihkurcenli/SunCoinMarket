package com.autumnsun.suncoinmarket.features.feature_favorite.domain.use_cases

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import com.autumnsun.suncoinmarket.features.feature_favorite.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<FavoriteCoinModel>>> {
        return repository.getAllFavoriteCoins()
    }
}