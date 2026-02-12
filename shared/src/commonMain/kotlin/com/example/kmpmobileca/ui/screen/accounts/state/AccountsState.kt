package com.example.kmpmobileca.ui.screen.accounts.state

import com.example.kmpmobileca.ui.screen.accounts.model.BankUi

data class AccountsState (
    val isLoading: Boolean,
    val caBanks: List<BankUi>,
    val otherBanks: List<BankUi>,
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