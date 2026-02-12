package com.example.kmpmobileca.data.repository

import com.example.kmpmobileca.data.mapping.toBankDomain
import com.example.kmpmobileca.data.remote.BankApi
import com.example.kmpmobileca.domain.model.Bank
import com.example.kmpmobileca.domain.repository.BankRepository

class BankRepositoryImpl(
    private val api: BankApi
) : BankRepository {
    override suspend fun getBanks(): List<Bank> =
        api.fetchBanks().map { it.toBankDomain() }
}