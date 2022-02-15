package com.autumnsun.suncoinmarket.features.feature_detail.data.model

import com.google.gson.annotations.SerializedName

data class DescriptionDto(
    @SerializedName("en")
    val en: String,
    @SerializedName("tr")
    val tr: String
)