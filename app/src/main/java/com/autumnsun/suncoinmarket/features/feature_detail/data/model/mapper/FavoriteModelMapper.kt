package com.autumnsun.suncoinmarket.features.feature_detail.data.model.mapper

import com.autumnsun.suncoinmarket.data.local.entity.FavoriteCoinEntity
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel


fun FavoriteCoinModel.toFavoriteEntity(): FavoriteCoinEntity {
    return FavoriteCoinEntity(
        id = id, coinName = coinName, imageUrl = imageUrl, symbol = symbol
    )
}