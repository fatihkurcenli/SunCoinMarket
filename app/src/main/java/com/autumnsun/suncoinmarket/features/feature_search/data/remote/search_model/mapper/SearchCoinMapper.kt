package com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.mapper

import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.SearchCoinDto
import com.autumnsun.suncoinmarket.features.feature_search.domain.data.SearchCoin

fun SearchCoinDto.toSearchCoin(): SearchCoin {
    return SearchCoin(
        coins = coins!!,
        exchanges = exchanges!!
    )
}