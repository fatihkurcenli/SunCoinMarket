package com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases

import com.autumnsun.suncoinmarket.features.feature_detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IsFavoriteUseCase(
    val repository: DetailRepository
) {
    operator fun invoke(id: String): Flow<Boolean> = flow {
        val isFavorite = repository.isFavoriteCoin(id)
        emit(isFavorite.data != null && isFavorite.data)
    }
}