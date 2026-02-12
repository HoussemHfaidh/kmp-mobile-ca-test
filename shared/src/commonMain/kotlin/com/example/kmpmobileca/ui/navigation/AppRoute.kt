package com.example.kmpmobileca.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute {

    @Serializable
    data object AccountsList : AppRoute

    @Serializable
    data class OperationsDetail(val accountId: String) : AppRoute

}