package com.inbedroom.couriertracking.core.di.feature

import com.inbedroom.couriertracking.data.network.TrackingRemoteRepository
import com.inbedroom.couriertracking.data.network.TrackingRemoteRepositoryImpl
import com.inbedroom.couriertracking.data.network.api.TrackApi
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