package com.example.kmpmobileca.domain.repository

import com.example.kmpmobileca.domain.model.Bank

interface BankRepository {
    suspend fun getBanks(): List<Bank>
}