package com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.mapper

import com.autumnsun.suncoinmarket.features.feature_home.data.model.HomeModelDtoItem
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.Coin
import com.autumnsun.suncoinmarket.features.feature_search.data.remote.search_model.SearchCoinDto
import com.autumnsun.suncoinmarket.features.feature_search.domain.data.SearchCoin

fun SearchCoinDto.toSearchCoin(): SearchCoin {
    return SearchCoin(
        coins = coins!!,
        exchanges = exchanges!!
    )
}

fun HomeModelDtoItem.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        large = image,
        marketCapRank = marketCapRank,
        symbol = symbol,
        thumb = image
    )
}