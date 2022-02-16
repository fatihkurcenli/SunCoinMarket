package com.autumnsun.suncoinmarket.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCoinEntity(
    @PrimaryKey
    val id: String,
    val coinName: String,
    val imageUrl: String,
)
