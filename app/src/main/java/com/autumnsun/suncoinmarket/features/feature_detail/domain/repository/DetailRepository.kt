package com.autumnsun.suncoinmarket.features.feature_detail.domain.repository

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_detail.data.model.CoinDetailDto
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel

interface DetailRepository {
    suspend fun getCoinById(id: String): Resource<CoinDetailDto>
    suspend fun favoriteCoin(favoriteModel: FavoriteCoinModel): Resource<Boolean>
}