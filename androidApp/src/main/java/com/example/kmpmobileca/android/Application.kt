package com.example.kmpmobileca.android

import android.app.Application
import com.example.kmpmobileca.initKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}