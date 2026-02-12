package com.example.kmpmobileca.di
import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        dispatcherModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}