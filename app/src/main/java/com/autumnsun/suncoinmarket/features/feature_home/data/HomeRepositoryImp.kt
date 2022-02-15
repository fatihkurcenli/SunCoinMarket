package com.autumnsun.suncoinmarket.features.feature_home.data

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.autumnsun.suncoinmarket.core.utils.Constants.PER_PAGE
import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem
import com.autumnsun.suncoinmarket.features.feature_home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val apiService: CryptoApi,
    private val context: Context
) : HomeRepository {
    override fun getAllCoins(): Flow<PagingData<HomeModelDtoItem>> {
        return Pager(
            config = PagingConfig(pageSize = PER_PAGE),
            pagingSourceFactory = { CoinDataSource(apiService, context) }
        ).flow
    }
}