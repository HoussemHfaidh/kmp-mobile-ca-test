package com.example.kmpmobileca.ui.screen.accounts.mapping

import com.example.kmpmobileca.domain.model.Account
import com.example.kmpmobileca.domain.model.Bank
import com.example.kmpmobileca.ui.screen.accounts.model.AccountUi
import com.example.kmpmobileca.ui.screen.accounts.model.BankUi
import com.example.kmpmobileca.ui.utils.formatEuro

fun Bank.toUi(): BankUi {
    val total = accounts.sumOf { it.balance }
    return BankUi(
        name = name,
        totalText = formatEuro(total),
        accounts = accounts.map { it.toUi() }
    )
}

fun Account.toUi(): AccountUi = AccountUi(
    id = id,
    label = label,
    balanceText = formatEuro(balance)
)