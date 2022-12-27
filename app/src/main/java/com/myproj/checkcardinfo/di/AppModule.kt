package com.myproj.checkcardinfo.di

import android.content.Context
import androidx.room.Room
import com.myproj.checkcardinfo.data.CardRepository
import com.myproj.checkcardinfo.data.CardRepositoryImpl
import com.myproj.checkcardinfo.data.db.CardDataBase
import com.myproj.checkcardinfo.data.db.dao.CardDao
import com.myproj.checkcardinfo.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCardRepository(cardDao: CardDao): CardRepository {
        return CardRepositoryImpl(cardDao)
    }

    @Singleton
    @Provides
    fun provideCarDataBase(@ApplicationContext context: Context): CardDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            CardDataBase::class.java,
            Constant.CARD_DATA_BASE
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideCardDao(cardDataBase: CardDataBase): CardDao = cardDataBase.cardDao()
}