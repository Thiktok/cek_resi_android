package com.ramadhan.couriertracking

import android.app.Application
import com.ramadhan.couriertracking.utils.Injector

class CourierTrackingApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.setup(this)
    }
}