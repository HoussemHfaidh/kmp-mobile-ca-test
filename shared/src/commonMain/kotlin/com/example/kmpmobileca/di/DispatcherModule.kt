package com.example.kmpmobileca.di

import com.example.kmpmobileca.core.coroutines.AppDispatchers
import com.example.kmpmobileca.core.coroutines.getAppDispatchers
import org.koin.dsl.module

val dispatcherModule = module {
    single<AppDispatchers> { getAppDispatchers() }
}
