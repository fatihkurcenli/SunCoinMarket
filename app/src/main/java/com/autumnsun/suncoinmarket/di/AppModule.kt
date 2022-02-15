package com.autumnsun.suncoinmarket.di

import android.content.Context
import android.content.SharedPreferences
import com.autumnsun.suncoinmarket.BuildConfig
import com.autumnsun.suncoinmarket.core.utils.Constants.CONNECT_TIMEOUT
import com.autumnsun.suncoinmarket.core.utils.Constants.READ_TIMEOUT
import com.autumnsun.suncoinmarket.core.utils.Constants.SHARED_PREFERENCES_NAME
import com.autumnsun.suncoinmarket.core.utils.Constants.WRITE_TIMEOUT
import com.autumnsun.suncoinmarket.core.utils.NetworkConnectionInterceptor
import com.autumnsun.suncoinmarket.data.remote.CryptoApi
import com.autumnsun.suncoinmarket.features.feature_search.data.repository.SearchRepositoryImp
import com.autumnsun.suncoinmarket.features.feature_search.domain.repository.SearchRepository
import com.autumnsun.suncoinmarket.features.feature_search.domain.use_case.SearchQueryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSunChatAppCase(
        searchRepository: SearchRepository,
        @ApplicationContext context: Context
    ): SearchQueryUseCase {
        return SearchQueryUseCase(searchRepository, context)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        apiService: CryptoApi
    ): SearchRepository {
        return SearchRepositoryImp(apiService)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(NetworkConnectionInterceptor(context))
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CryptoApi {
        return retrofit.create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }
}