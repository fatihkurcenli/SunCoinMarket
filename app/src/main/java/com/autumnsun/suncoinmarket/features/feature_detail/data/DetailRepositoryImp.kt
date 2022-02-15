package com.autumnsun.suncoinmarket.features.feature_detail.data

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_detail.data.model.CoinDetailDto
import com.autumnsun.suncoinmarket.features.feature_detail.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val apiService: CryptoApi
) : DetailRepository {

    override suspend fun getCoinById(id: String): Resource<CoinDetailDto> {
        return try {
            val response = apiService.getCoinByID(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error("Empty body response")
            } else {
                Resource.Error("Failed request server!")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}