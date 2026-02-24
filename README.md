# **📡 Android Network Connectivity Checker**


---
A simple and lightweight Android Library to detect internet connectivity in real-time using ConnectivityManager.NetworkCallback.

- This library allows you to:

- ✅ Check internet availability

- ✅ Listen for real-time network changes

- ✅ Show/Hide layouts dynamically

- ✅ No BroadcastReceiver needed

- ✅ Clean and minimal implementation

---

## ✨ **Features**

- Real-time network monitoring

- Supports WiFi, Mobile Data & Ethernet

- Simple callback-based listener

- Easy integration

- Lightweight & efficient





  ---

# **Preview**
---
<p align="center">
  <img src="https://github.com/S13reya/Android_NetworkConnectivityChecker/blob/stages/app/src/main/assets/demovideo.gif" height="320"/>




</p>


## ⚡ **Installation**

**Step 1:** Add JitPack repository to your root build.gradle:

```gradle
maven { url = uri("https://jitpack.io") }
```

**Step 2:** Add the dependency in your app `build.gradle` (example if hosted on JitPack):  

```gradle
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_NetworkConnectivityChecker:1.0.0'

}
```

## ⚡ **Usage**

1. Add in XML

```
 <?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">

    <LinearLayout
        android:id="@+id/internetLayout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

        <TextView
            android:text="Internet Connected"
            android:textSize="20sp"
            android:textColor="@android:color/holo_green_dark"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    </LinearLayout>

    <LinearLayout
        android:id="@+id/noInternetLayout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

        <TextView
            android:text="No Internet Connection"
            android:textSize="20sp"
            android:textColor="@android:color/holo_red_dark"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"/>
    </LinearLayout>

</LinearLayout>
```

## ⚡ **Main Activity**
```
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
```





## **📄 License**

**MIT License**  
```
Copyright (c) 2025 Excelsior Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED **"AS IS"**, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```



  
