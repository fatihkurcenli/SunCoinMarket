package com.autumnsun.suncoinmarket.features.feature_favorite.data.mapper

import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.Coin


fun FavoriteCoinModel.toCoin(): Coin {
    return Coin(
        id = id, null, null, null, null, null
    )
}