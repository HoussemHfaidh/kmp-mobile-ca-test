package com.example.kmpmobileca.domain.usecase

import com.example.kmpmobileca.domain.model.Account
import com.example.kmpmobileca.domain.model.Operation
import com.example.kmpmobileca.domain.repository.BankRepository

class GetOperationsUseCase(
    private val repo: BankRepository
) {
    suspend operator fun invoke(accountId: String): Account {

        val banks = repo.getBanks()
        val account = banks.flatMap { it.accounts }
            .firstOrNull { it.id == accountId }
            ?: error("Account not found")

        val sortedOps = account.operations.sortedWith(
            compareByDescending<Operation> { it.date }
                .thenBy { it.title.lowercase() }
        )
        return account.copy(operations = sortedOps)
    }
}