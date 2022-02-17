package com.autumnsun.suncoinmarket.features.feature_detail.data.model.mapper

import com.autumnsun.suncoinmarket.data.local.entity.FavoriteCoinEntity
import com.autumnsun.suncoinmarket.features.feature_auth.data.UserModel
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel


fun FavoriteCoinModel.toFavoriteEntity(): FavoriteCoinEntity {
    return FavoriteCoinEntity(
        id = id, coinName = coinName, imageUrl = imageUrl, symbol = symbol
    )
}

fun UserModel.toFavoriteCoinList(): List<FavoriteCoinEntity> {
    val arrayList = ArrayList<FavoriteCoinEntity>()
    favoriteCoinList.forEach { favoriteCoinModel ->
        arrayList.add(favoriteCoinModel.toFavoriteEntity())
    }
    return arrayList
}