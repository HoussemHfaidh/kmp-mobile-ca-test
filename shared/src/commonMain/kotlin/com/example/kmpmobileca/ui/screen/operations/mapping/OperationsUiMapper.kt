package com.example.kmpmobileca.ui.screen.operations.mapping

import com.example.kmpmobileca.domain.model.Account
import com.example.kmpmobileca.domain.model.Operation
import com.example.kmpmobileca.ui.screen.operations.model.OperationUi
import com.example.kmpmobileca.ui.screen.operations.model.OperationsUi
import com.example.kmpmobileca.ui.utils.formatDate
import com.example.kmpmobileca.ui.utils.formatEuro

fun Account.toOperationsUi(): OperationsUi {
    return OperationsUi(
        accountLabel = label,
        balanceText = formatEuro(balance),
        items = operations
            .map { it.toUi() }
    )
}

fun Operation.toUi() = OperationUi(
    id = id,
    title = title,
    amountText = formatEuro(amount),
    dateText = formatDate(date)
)