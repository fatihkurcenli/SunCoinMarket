package com.autumnsun.suncoinmarket.features.feature_search.domain.data

import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.Coin
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.Exchange

data class SearchCoin(
    val coins: List<Coin>,
    val exchanges: List<Exchange>
)