package com.inbedroom.couriertracking

import androidx.multidex.MultiDexApplication
import com.inbedroom.couriertracking.core.di.ApplicationComponent
import com.inbedroom.couriertracking.core.di.ApplicationModule
import com.inbedroom.couriertracking.core.di.DaggerApplicationComponent

class CourierTrackingApplication: MultiDexApplication() {
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        appComponent.inject(this)
    }
}