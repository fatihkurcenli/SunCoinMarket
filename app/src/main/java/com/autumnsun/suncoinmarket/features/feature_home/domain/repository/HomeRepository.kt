package com.autumnsun.suncoinmarket.features.feature_home.domain.repository

import androidx.paging.PagingData
import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllCoins(): Flow<PagingData<HomeModelDtoItem>>
}