package com.example.kmpmobileca.ui.screen.accounts

import com.example.kmpmobileca.core.coroutines.AppDispatchers
import com.example.kmpmobileca.domain.usecase.GetBanksUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountsListViewModel(
    private val getBanksUseCase: GetBanksUseCase,
    dispatchers: AppDispatchers
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatchers.main)
    private val _state = MutableStateFlow(AccountsState.initialValue)
    val state: StateFlow<AccountsState> = _state.asStateFlow()

    fun getBanks() {
        _state.value = _state.value.copy(isLoading = true)

        scope.launch {
            runCatching { getBanksUseCase() }
                .onSuccess { result ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        caBanks = result.creditAgricoleBanks,
                        otherBanks = result.otherBanks
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