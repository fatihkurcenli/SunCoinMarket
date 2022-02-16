package com.autumnsun.suncoinmarket.features.feature_detail.data.model.mapper

import com.autumnsun.suncoinmarket.features.feature_detail.data.model.*
import com.autumnsun.suncoinmarket.features.feature_detail.domain.data.*

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        id = id,
        name = name,
        symbol = symbol,
        description = description.toDescription(),
        hashingAlgorithm = hashingAlgorithm ?: "",
        image = image.toImage(),
        marketData = marketData.toMarketData(),
        lastUpdated = lastUpdated
    )
}

fun DescriptionDto.toDescription(): Description {
    return Description(
        en = en, tr = tr
    )
}

fun ImageDto.toImage(): Image {
    return Image(
        large = large, small = small, thumb = thumb
    )
}

fun MarketDataDto.toMarketData(): MarketData {
    return MarketData(
        currentPrice = currentPrice.toCurrentPrice(),
        lastUpdated = lastUpdated,
        priceChange24h = priceChange24h,
        priceChangePercentage24h = priceChangePercentage24h,
        highestPrice24h = highestPrice24h.toHigh(),
        sparkline = sparklineDto7D.toSparkLine(),
        lowestPrice24h = lowestPrice24h.toLow()

    )
}

fun CurrentPriceDto.toCurrentPrice(): CurrentPrice {
    return CurrentPrice(
        tl = tl, usd = usd
    )
}

fun HighDto.toHigh(): High {
    return High(
        usd = usd
    )
}

fun LowDto.toLow(): Low {
    return Low(
        usd = usd
    )
}

fun SparklineDto.toSparkLine(): Sparkline {
    return Sparkline(
        price = price
    )
}

