package com.autumnsun.suncoinmarket.data.remote

import com.autumnsun.suncoinmarket.features.feature_detail.data.model.CoinDetailDto
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.SearchCoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("search")
    suspend fun searchCrypto(@Query("query") searchCrypto: String): Response<SearchCoinDto>

    @GET("coins/{id}/sparkline=true")
    suspend fun getCoinByID(@Path("id") id: String): Response<CoinDetailDto>
}