package com.example.kmpmobileca.ui.screen.operations

import com.example.kmpmobileca.core.coroutines.AppDispatchers
import com.example.kmpmobileca.domain.usecase.GetOperationsUseCase
import com.example.kmpmobileca.ui.screen.operations.mapping.toOperationsUi
import com.example.kmpmobileca.ui.screen.operations.state.OperationsDetailsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OperationsDetailsViewModel(
    private val getOperationsUseCase: GetOperationsUseCase,
    dispatchers: AppDispatchers
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatchers.main)
    private val _state = MutableStateFlow(OperationsDetailsState.initialValue)
    val state: StateFlow<OperationsDetailsState> = _state.asStateFlow()

    fun getOperations(accountId: String){
        _state.value = _state.value.copy(isLoading = true)
        scope.launch {
            runCatching { getOperationsUseCase(accountId) }
                .onSuccess { account ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        operations = account.toOperationsUi()
                    )
                }
                .onFailure { t ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = t.message ?: "Unknown error"
                    )
                }
        }
    }
}