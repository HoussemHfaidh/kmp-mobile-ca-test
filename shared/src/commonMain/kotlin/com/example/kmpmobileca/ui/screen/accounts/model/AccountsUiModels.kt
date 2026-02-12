package com.example.kmpmobileca.ui.screen.accounts.model

data class BankUi(
    val name: String,
    val totalText: String,
    val accounts: List<AccountUi>
)

data class AccountUi(
    val id: String,
    val label: String,
    val balanceText: String
)