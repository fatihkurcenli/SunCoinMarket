package com.autumnsun.suncoinmarket.features.feature_search.domain.use_case

import android.content.Context
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.util.Resource
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.mapper.toSearchCoin
import com.autumnsun.suncoinmarket.features.feature_search.domain.data.SearchCoin
import com.autumnsun.suncoinmarket.features.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SearchQueryUseCase(
    private val repository: SearchRepository,
    private val context: Context
) {
    suspend operator fun invoke(searchCryptoName: String): Flow<Resource<SearchCoin>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.searchCrypto(searchCryptoName)
            if (response.isSuccessful) {
                response.body()?.let {
                    val sortedListMarkedRank = it.coins?.sortedBy { sorted -> sorted.marketCapRank }
                    val sortedList = it.copy(coins = sortedListMarkedRank)
                    emit(Resource.Success(sortedList.toSearchCoin()))
                } ?: emit(Resource.Error(context.getString(R.string.empty_data_coming_error)))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(context.getString(R.string.error_network_call)))
        } catch (e: IOException) {
            emit(Resource.Error(context.getString(R.string.error_network_call_internet_connection)))
        }
    }
}