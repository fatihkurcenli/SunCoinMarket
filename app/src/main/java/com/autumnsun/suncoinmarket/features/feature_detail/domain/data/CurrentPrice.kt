package com.autumnsun.suncoinmarket.features.feature_detail.domain.data

import com.google.gson.annotations.SerializedName

data class CurrentPrice(
    @SerializedName("try")
    val tl: Double,
    @SerializedName("usd")
    val usd: Double,
)