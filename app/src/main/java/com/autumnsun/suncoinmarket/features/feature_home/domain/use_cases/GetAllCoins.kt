package com.autumnsun.suncoinmarket.features.feature_home.domain.use_cases

import androidx.paging.PagingData
import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem
import com.autumnsun.suncoinmarket.features.feature_home.domain.repository.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow


class GetAllCoins(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<PagingData<HomeModelDtoItem>> {
        return repository.getAllCoins()
    }
}