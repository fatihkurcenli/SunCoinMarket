package com.autumnsun.suncoinmarket.features.feature_detail.data.model


import com.google.gson.annotations.SerializedName

data class SparklineDto(
    @SerializedName("price")
    val price: List<Double>?
)