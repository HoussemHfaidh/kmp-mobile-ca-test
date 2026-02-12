package com.example.kmpmobileca.di

import com.example.kmpmobileca.domain.usecase.GetBanksUseCase
import com.example.kmpmobileca.domain.usecase.GetOperationsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetBanksUseCase(get()) }
    factory { GetOperationsUseCase(get()) }
}