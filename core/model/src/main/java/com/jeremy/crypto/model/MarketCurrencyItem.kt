package com.jeremy.crypto.model

import com.jeremy.crypto.model.BigDecimalMapper.newBigDecimal
import kotlinx.serialization.Serializable

@Serializable
data class MarketCurrencyItem(
    val acc_trade_price: Double,
    val acc_trade_price_24h: Double,
    val acc_trade_volume: Double,
    val acc_trade_volume_24h: Double,
    val change: String,
    val change_price: Double,
    val change_rate: Double,
    val high_price: Double,
    val highest_52_week_date: String,
    val highest_52_week_price: Double,
    val low_price: Double,
    val lowest_52_week_date: String,
    val lowest_52_week_price: Double,
    val market: String,
    val opening_price: Double,
    val prev_closing_price: Double,
    val signed_change_price: Double,
    val signed_change_rate: Double,
    val timestamp: Long,
    val trade_date: String,
    val trade_price: Double,
    val trade_time: String,
    val trade_timestamp: Long,
    val trade_volume: Double
) {
    fun mapTo(): CurrencyUiItem {
        return CurrencyUiItem(
            market = market,
            tradeDate = trade_date,
            tradePrice = trade_price.newBigDecimal(),
            tradeTime = trade_time,
            tradeVolume = trade_volume,
            accTradePrice = acc_trade_price_24h.newBigDecimal(),
            change = change,
            changePrice = change_price,
            changeRate = change_rate * 100,
            highPrice = high_price,
            lowPrice = low_price,
            openingPrice = opening_price,
            prevClosingPrice = prev_closing_price,
            signedChangePrice = signed_change_price,
            signedChangeRate = signed_change_rate * 100,
            timestamp = timestamp
        )
    }
}