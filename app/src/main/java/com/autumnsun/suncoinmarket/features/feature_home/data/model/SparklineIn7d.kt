package com.autumnsun.suncoinmarket.features.feature_home.data.model


import com.google.gson.annotations.SerializedName

data class SparklineIn7d(
    @SerializedName("price")
    val price: List<Double>?
)