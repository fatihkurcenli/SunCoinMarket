package com.autumnsun.suncoinmarket.di

import android.content.Context
import androidx.room.Room
import com.autumnsun.suncoinmarket.data.local.SunCoinMarketDb
import com.autumnsun.suncoinmarket.data.local.SunCoinMarketDb.Companion.DB_NAME
import com.autumnsun.suncoinmarket.data.local.dao.CoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SunCoinMarketDb = Room.databaseBuilder(context, SunCoinMarketDb::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(database: SunCoinMarketDb): CoinDao = database.coinDao()

}