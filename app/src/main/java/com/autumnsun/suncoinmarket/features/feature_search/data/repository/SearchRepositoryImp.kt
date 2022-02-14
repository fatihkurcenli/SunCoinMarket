package com.autumnsun.suncoinmarket.features.feature_search.data.repository

import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.SearchCoinDto
import com.autumnsun.suncoinmarket.features.feature_search.domain.repository.SearchRepository
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val apiService: CryptoApi
) : SearchRepository {
    override suspend fun searchCrypto(cryptoName: String): Response<SearchCoinDto> {
        return apiService.searchCrypto(cryptoName)
    }
}