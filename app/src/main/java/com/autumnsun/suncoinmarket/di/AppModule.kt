package com.autumnsun.suncoinmarket.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDb(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideChatRepository(
        db: FirebaseDatabase,
        auth: FirebaseAuth,
        dataStore: DataStoreUtil
    ): ChatRepository {
        return ChatAppImpl(db, auth, dataStore)
    }

    @Provides
    @Singleton
    fun provideSunChatAppCase(
        chatRepository: ChatRepository
    ): SunChatAppCase {
        return SunChatAppCase(
            signUpCase = SignUpCase(chatRepository)
        )
    }
}