package com.autumnsun.suncoinmarket.data.local.dao

import androidx.room.*
import com.autumnsun.suncoinmarket.data.local.entity.FavoriteCoinEntity

@Dao
interface CoinDao {

    @Query("SELECT * FROM FavoriteCoinEntity WHERE id=:id")
    suspend fun getCoinById(id: String): FavoriteCoinEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCoins(listCrypto: List<FavoriteCoinEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCoin(favoriteCoinEntity: FavoriteCoinEntity)

    @Query("SELECT * FROM FavoriteCoinEntity")
    suspend fun getFavoriteCoins(): List<FavoriteCoinEntity>

    @Delete
    suspend fun deleteFavorite(favoriteCoinEntity: FavoriteCoinEntity)
}