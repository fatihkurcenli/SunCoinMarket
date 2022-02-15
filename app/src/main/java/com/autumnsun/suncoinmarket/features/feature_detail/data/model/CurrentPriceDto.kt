package com.autumnsun.suncoinmarket.features.feature_detail.data.model

import com.google.gson.annotations.SerializedName

data class CurrentPriceDto(
    @SerializedName("try")
    val tl: Double,
    @SerializedName("usd")
    val usd: Double,
)