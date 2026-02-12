package com.example.kmpmobileca.core.coroutines

import kotlinx.coroutines.Dispatchers

class AndroidDispatchers : AppDispatchers {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
    override val default = Dispatchers.Default
}

actual fun getAppDispatchers(): AppDispatchers = AndroidDispatchers()