package com.ramadhan.couriertracking.core.di

import com.ramadhan.couriertracking.CourierTrackingApplication
import com.ramadhan.couriertracking.core.di.feature.HistoryModule
import com.ramadhan.couriertracking.core.di.feature.TrackingModule
import com.ramadhan.couriertracking.view.MainActivity
import com.ramadhan.couriertracking.view.TrackingDetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        MVVMModule::class,
        HistoryModule::class,
        TrackingModule::class
    ]
)
interface ApplicationComponent {
    fun inject(application: CourierTrackingApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(trackingDetailActivity: TrackingDetailActivity)
}