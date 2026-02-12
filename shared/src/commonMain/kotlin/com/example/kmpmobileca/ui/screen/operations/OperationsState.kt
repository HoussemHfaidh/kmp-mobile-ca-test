package com.example.kmpmobileca.ui.screen.operations

import com.example.kmpmobileca.domain.model.Operation

data class OperationsDetailsState(
    val isLoading: Boolean,
    val operations: List<Operation>,
    val balance: Double,
    val accountLabel: String,
    val error: String?
) {
    companion object {
        val initialValue = OperationsDetailsState(
            isLoading = false,
            operations = emptyList(),
            balance = 0.0,
            accountLabel = "",
            error = null
        )
    }
}