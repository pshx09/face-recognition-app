package com.example.facerecognitionapp

import android.app.Application
import com.cloudinary.Configuration
import com.cloudinary.android.MediaManager






class YourApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = Configuration.Builder()
            .setCloudName("dfys2b3e7")
            .setApiKey("394176383539522")
            .setApiSecret("FeTpR1vWXzb2noa2hzqsNxYFWCM")
            .build()
        MediaManager.init(applicationContext, config)

    }
}
