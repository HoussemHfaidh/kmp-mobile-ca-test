package com.example.kmpmobileca.ui.screen.operations.state

import com.example.kmpmobileca.ui.screen.operations.model.OperationsUi

data class OperationsDetailsState(
    val isLoading: Boolean,
    val operations: OperationsUi,
    val error: String?
) {
    companion object {
        val initialValue = OperationsDetailsState(
            isLoading = false,
            operations = OperationsUi(
                accountLabel = "",
                balanceText = "",
                items = emptyList()
            ),
            error = null
        )
    }
}