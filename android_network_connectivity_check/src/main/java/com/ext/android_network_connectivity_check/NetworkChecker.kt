package com.ext.android_network_connectivity_check

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

class NetworkChecker(private val context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    fun registerListener(onNetworkChange: (Boolean) -> Unit) {

        networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                onNetworkChange(true)
            }

            override fun onLost(network: Network) {
                onNetworkChange(false)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback!!)
    }

    fun unregisterListener() {
        networkCallback?.let {
            connectivityManager.unregisterNetworkCallback(it)
        }
    }

    fun isInternetAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}