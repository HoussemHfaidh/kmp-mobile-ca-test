package com.example.kmpmobileca.core.ktor

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun getPlatformEngine(): HttpClientEngine = Darwin.create()