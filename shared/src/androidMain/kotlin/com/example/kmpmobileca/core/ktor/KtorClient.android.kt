package com.example.kmpmobileca.core.ktor

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual fun getPlatformEngine(): HttpClientEngine = OkHttp.create()