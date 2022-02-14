package com.autumnsun.suncoinmarket.data.remote

import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.SearchCoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("search")
    suspend fun searchCrypto(@Query("query") searchCrypto: String): Response<SearchCoinDto>

}