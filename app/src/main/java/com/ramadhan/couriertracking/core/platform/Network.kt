package com.ramadhan.couriertracking.core.platform

import android.net.ConnectivityManager
import android.net.Network

object Network{
    fun isConnected(): Boolean {
        var result = false
        val network = object: ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                result = true
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                result = false
            }

            override fun onUnavailable() {
                super.onUnavailable()
                result = false
            }
        }
        return result
    }
}