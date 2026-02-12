package com.example.kmpmobileca.di

import com.example.kmpmobileca.core.ktor.createHttpClient
import com.example.kmpmobileca.data.remote.BankApi
import com.example.kmpmobileca.data.remote.BankApiImpl
import com.example.kmpmobileca.data.remote.RemoteConfig
import com.example.kmpmobileca.data.repository.BankRepositoryImpl
import com.example.kmpmobileca.domain.repository.BankRepository
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient() }
    single<BankApi> { BankApiImpl(client = get(), baseUrl = RemoteConfig.BASE_URL) }
}