package com.autumnsun.suncoinmarket.features.feature_detail.data.model

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("thumb")
    val thumb: String
)
