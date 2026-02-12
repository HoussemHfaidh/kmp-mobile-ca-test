package com.example.kmpmobileca.data.remote

import com.example.kmpmobileca.data.dto.BankDto

interface BankApi {
    suspend fun fetchBanks(): List<BankDto>
}