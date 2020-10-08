package com.ramadhan.couriertracking

import android.app.Application
import com.ramadhan.couriertracking.core.di.ApplicationComponent
import com.ramadhan.couriertracking.core.di.ApplicationModule
import com.ramadhan.couriertracking.core.di.DaggerApplicationComponent
//import com.ramadhan.couriertracking.utils.Injector

class CourierTrackingApplication: Application() {
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
//        Injector.setup(this)
        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        appComponent.inject(this)
    }
}