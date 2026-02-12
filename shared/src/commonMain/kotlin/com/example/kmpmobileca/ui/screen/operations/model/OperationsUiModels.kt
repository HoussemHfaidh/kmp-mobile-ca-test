package com.example.kmpmobileca.ui.screen.operations.model

data class OperationsUi(
    val accountLabel: String,
    val balanceText: String,
    val items: List<OperationUi>
)

data class OperationUi(
    val id: String,
    val title: String,
    val amountText: String,
    val dateText: String
)