package com.example.kmpmobileca.core.coroutines


import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}

expect fun getAppDispatchers(): AppDispatchers