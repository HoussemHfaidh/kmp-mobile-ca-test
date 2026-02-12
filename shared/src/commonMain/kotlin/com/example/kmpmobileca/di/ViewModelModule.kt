package com.example.kmpmobileca.di

import com.example.kmpmobileca.core.coroutines.getAppDispatchers
import com.example.kmpmobileca.ui.screen.accounts.AccountsListViewModel
import com.example.kmpmobileca.ui.screen.operations.OperationsDetailsViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { AccountsListViewModel(getBanksUseCase = get(), dispatchers = getAppDispatchers()) }
    factory { OperationsDetailsViewModel(getOperationsUseCase = get(), dispatchers = getAppDispatchers()) }
}