package com.example.kmpmobileca


import com.example.kmpmobileca.di.appModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}