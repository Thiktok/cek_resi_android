package com.ramadhan.couriertracking.core.di.feature

import android.content.Context
import com.ramadhan.couriertracking.data.room.HistoryRepository
import com.ramadhan.couriertracking.data.room.HistoryRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class HistoryModule {

    @Provides
    fun provideHistoryRepository(context: Context): HistoryRepository = HistoryRepositoryImpl(context)

}