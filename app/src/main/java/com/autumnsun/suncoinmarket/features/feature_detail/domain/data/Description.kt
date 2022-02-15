package com.autumnsun.suncoinmarket.features.feature_detail.domain.data

import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("en")
    val en: String,
    @SerializedName("tr")
    val tr: String
)