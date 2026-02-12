package com.example.kmpmobileca.di

import com.example.kmpmobileca.data.repository.BankRepositoryImpl
import com.example.kmpmobileca.domain.repository.BankRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BankRepository> { BankRepositoryImpl(api = get()) }
}