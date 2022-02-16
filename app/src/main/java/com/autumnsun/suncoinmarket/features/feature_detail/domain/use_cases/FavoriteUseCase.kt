package com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import com.autumnsun.suncoinmarket.features.feature_detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteUseCase(
    private val repository: DetailRepository
) {

    operator fun invoke(favoriteCoin: FavoriteCoinModel): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        val success = repository.favoriteCoin(favoriteCoin)
        if (success.message?.isNotBlank() == true) {
            emit(Resource.Error(success.message))
        }
        if (success.data == true) {
            emit(Resource.Success(true))
        } else {
            emit(Resource.Success(false))
        }
    }
}