package com.ramadhan.couriertracking.core.di.feature

import android.content.Context
import com.ramadhan.couriertracking.data.room.HistoryDao
import com.ramadhan.couriertracking.data.room.HistoryDatabase
import com.ramadhan.couriertracking.data.room.HistoryRepository
import com.ramadhan.couriertracking.data.room.HistoryRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HistoryModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = HistoryDatabase.invoke(context)

    @Singleton
    @Provides
    fun provideDao(db: HistoryDatabase) = db.historyDao()

    @Provides
    fun provideHistoryRepository(dao: HistoryDao): HistoryRepository = HistoryRepositoryImpl(dao)

}