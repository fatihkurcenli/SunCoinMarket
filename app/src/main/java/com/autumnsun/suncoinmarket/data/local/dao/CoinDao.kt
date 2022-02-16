package com.autumnsun.suncoinmarket.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.autumnsun.suncoinmarket.data.local.entity.FavoriteCoinEntity

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCoins(listCrypto: List<FavoriteCoinEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCoin(listCrypto: FavoriteCoinEntity)

    @Query("SELECT * FROM FavoriteCoinEntity")
    suspend fun getFavoriteCoins(): List<FavoriteCoinEntity>
}