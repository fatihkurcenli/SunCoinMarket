package com.autumnsun.suncoinmarket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.autumnsun.suncoinmarket.data.local.SunCoinMarketDb.Companion.DB_VERSION
import com.autumnsun.suncoinmarket.data.local.dao.CoinDao
import com.autumnsun.suncoinmarket.data.local.entity.FavoriteCoinEntity

@Database(
    entities = [FavoriteCoinEntity::class],
    version = DB_VERSION, exportSchema = true
)
abstract class SunCoinMarketDb : RoomDatabase() {
    abstract fun coinDao(): CoinDao

    companion object {
        const val DB_NAME = "sun_coin_market_db"
        const val DB_VERSION = 1
    }
}