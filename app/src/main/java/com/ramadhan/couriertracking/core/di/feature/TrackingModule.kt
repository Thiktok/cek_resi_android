package com.ramadhan.couriertracking.core.di.feature

import com.ramadhan.couriertracking.data.network.TrackingRemoteRepository
import com.ramadhan.couriertracking.data.network.TrackingRemoteRepositoryImpl
import com.ramadhan.couriertracking.data.network.api.TrackApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TrackingModule {

    @Provides
    @Singleton
    fun provideTrackingApi(retrofit: Retrofit): TrackApi = retrofit.create(TrackApi::class.java)

    @Provides
    @Singleton
    fun provideTrackingRepository(trackApi: TrackApi): TrackingRemoteRepository =
        TrackingRemoteRepositoryImpl(trackApi)
}