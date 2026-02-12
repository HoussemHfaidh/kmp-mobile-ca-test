package com.example.kmpmobileca.data.remote

import com.example.kmpmobileca.data.dto.BankDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BankApiImpl(private val client: HttpClient,
                  private val baseUrl: String) : BankApi {
    override suspend fun fetchBanks(): List<BankDto> {
        return client.get(baseUrl).body()
    }
}