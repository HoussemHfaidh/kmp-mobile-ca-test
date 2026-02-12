package com.example.kmpmobileca.domain.usecase

import com.example.kmpmobileca.domain.model.Bank
import com.example.kmpmobileca.domain.repository.BankRepository

class GetBanksUseCase(
    private val repo: BankRepository
) {
    suspend operator fun invoke(): BanksResult {
        val banks = repo.getBanks()
        val sorted = banks.sortedBy { it.name.lowercase() }

        val creditAgricole = sorted.filter { it.isCreditAgricole }
        val other = sorted.filterNot { it.isCreditAgricole }

        return BanksResult(creditAgricoleBanks = creditAgricole, otherBanks = other)
    }
}

data class BanksResult(
    val creditAgricoleBanks: List<Bank>,
    val otherBanks: List<Bank>
)