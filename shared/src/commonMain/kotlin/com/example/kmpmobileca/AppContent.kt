package com.example.kmpmobileca

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.kmpmobileca.ui.navigation.AppRoute
import com.example.kmpmobileca.ui.screen.accounts.AccountsListScreen
import com.example.kmpmobileca.ui.screen.operations.OperationsDetailScreen
import com.example.kmpmobileca.ui.theme.CA_Gray_050

@Composable
fun AppContent() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = CA_Gray_050,
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = AppRoute.AccountsList,
            modifier = Modifier
                .padding(padding)
                .safeContentPadding()
        ) {
            composable<AppRoute.AccountsList> {
                AccountsListScreen(
                    openOperationsDetail = { accountId ->
                        navController.navigate(AppRoute.OperationsDetail(accountId = accountId))
                    }
                )
            }

            composable<AppRoute.OperationsDetail> { backStackEntry ->
                val route = backStackEntry.toRoute<AppRoute.OperationsDetail>()
                OperationsDetailScreen(accountId = route.accountId, onBack = {
                    navController.popBackStack()
                })
            }

        }
    }
}