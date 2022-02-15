package com.autumnsun.suncoinmarket.features.feature_home.data


import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem

class CoinDataSource(
    private val apiService: CryptoApi,
    private val context: Context
) : PagingSource<Int, HomeModelDtoItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeModelDtoItem> {
        val currentPage = params.key ?: START_PAGE
        return try {
            val response = apiService.getAllCoins(page = currentPage)
            val endOfPagination = response.isEmpty()
            if (response.isNotEmpty()) {
                LoadResult.Page(
                    data = response,
                    prevKey = if (currentPage == START_PAGE) null else currentPage.minus(1),
                    nextKey = if (endOfPagination) null else currentPage.plus(1)
                )
            } else {
                throw Exception(context.getString(R.string.empty_list_exception))
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HomeModelDtoItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val START_PAGE = 1
    }
}