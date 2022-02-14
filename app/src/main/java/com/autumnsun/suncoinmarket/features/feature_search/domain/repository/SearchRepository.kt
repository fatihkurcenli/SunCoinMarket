package com.autumnsun.suncoinmarket.features.feature_search.domain.repository

import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.SearchCoinDto
import retrofit2.Response

interface SearchRepository {
    suspend fun searchCrypto(cryptoName: String): Response<SearchCoinDto>
}