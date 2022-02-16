package com.autumnsun.suncoinmarket.features.feature_detail.domain.use_cases

import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_detail.data.model.mapper.toCoinDetail
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.CoinDetail
import com.autumnsun.suncoinmarket.features.feature_detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCoinUseCase(
    private val detailRepository: DetailRepository
) {
    operator fun invoke(id: String?): Flow<Resource<CoinDetail>> = flow {
        if (id == null) {
            emit(Resource.Error("Id comming null error!! "))
            return@flow
        }
        emit(Resource.Loading())
        val response = detailRepository.getCoinById(id)
        response.data?.let {
            emit(Resource.Success(it.toCoinDetail()))
        } ?: emit(Resource.Error("Data not loaded, pls try again"))
    }
}