package com.ext.android_networkconnectivitychecker

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ext.android_network_connectivity_check.NetworkChecker

class MainActivity : AppCompatActivity() {

    private lateinit var networkChecker: NetworkChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val internetLayout = findViewById<View>(R.id.internetLayout)
        val noInternetLayout = findViewById<View>(R.id.noInternetLayout)

        networkChecker = NetworkChecker(this)

        // Initial check
        if (networkChecker.isInternetAvailable()) {
            internetLayout.visibility = View.VISIBLE
            noInternetLayout.visibility = View.GONE
        } else {
            internetLayout.visibility = View.GONE
            noInternetLayout.visibility = View.VISIBLE
        }

        // Real-time listener
        networkChecker.registerListener { isConnected ->
            runOnUiThread {
                if (isConnected) {
                    internetLayout.visibility = View.VISIBLE
                    noInternetLayout.visibility = View.GONE
                } else {
                    internetLayout.visibility = View.GONE
                    noInternetLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkChecker.unregisterListener()
    }
}