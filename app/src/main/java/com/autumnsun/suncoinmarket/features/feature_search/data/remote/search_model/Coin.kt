package com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coin(
    @SerializedName("id")
    val id: String?,
    @SerializedName("large")
    val large: String?,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("thumb")
    val thumb: String?
) : Parcelable