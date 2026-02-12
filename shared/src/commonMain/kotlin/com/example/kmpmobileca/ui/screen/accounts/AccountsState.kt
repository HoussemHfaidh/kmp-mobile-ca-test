package com.example.kmpmobileca.ui.screen.accounts

import com.example.kmpmobileca.domain.model.Bank

data class AccountsState (
    val isLoading: Boolean,
    val caBanks: List<Bank>,
    val otherBanks: List<Bank>,
    val error: String?
){
    companion object {
        val initialValue = AccountsState(
            isLoading = false,
            caBanks = emptyList(),
            otherBanks = emptyList(),
            error = null
        )
    }
}