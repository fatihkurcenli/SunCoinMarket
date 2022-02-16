package com.autumnsun.suncoinmarket.features.feature_auth.data

import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.FavoriteCoinModel

data class UserModel(
    val userName: String? = "",
    val userId: String? = "",
    val email: String? = "",
    val password: String? = "",
    val favoriteCoinList: List<FavoriteCoinModel> = emptyList(),
    val token: String? = ""
)
