package com.example.kmpmobileca.ui.screen.accounts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kmpmobileca.ui.screen.accounts.model.AccountUi
import com.example.kmpmobileca.ui.screen.accounts.model.BankUi
import com.example.kmpmobileca.ui.theme.CA_Black
import com.example.kmpmobileca.ui.theme.CA_Gray_100
import com.example.kmpmobileca.ui.theme.CA_Gray_300
import com.example.kmpmobileca.ui.theme.CA_Gray_500
import com.example.kmpmobileca.ui.theme.spacing6XL
import com.example.kmpmobileca.ui.theme.spacingLg
import com.example.kmpmobileca.ui.theme.spacingMd
import com.example.kmpmobileca.ui.theme.spacingSm
import com.example.kmpmobileca.ui.theme.spacingXl
import org.koin.compose.koinInject

@Composable
fun AccountsListScreen(
    viewModel: AccountsListViewModel = koinInject(),
    openOperationsDetail: (id: String) -> Unit
) {

    /* ------------------------------------- */
    /*              State                    */
    /* ------------------------------------- */
    val state by viewModel.state.collectAsState()

    /* ------------------------------------- */
    /*              LaunchedEffect           */
    /* ------------------------------------- */

    LaunchedEffect(Unit) {
        viewModel.getBanks()
    }

    when {
        state.isLoading -> {
            LoadingContent()
        }

        state.error != null -> {
            ErrorContent(error = state.error.orEmpty())
        }

        else -> {
            LazyColumn(
                contentPadding = PaddingValues(vertical = spacingLg),
                verticalArrangement = Arrangement.spacedBy(spacingMd)
            ) {

                stickyHeader {
                    AccountsTitle()
                }

                item {
                    if (state.caBanks.isNotEmpty()) {
                        Text(
                            modifier = Modifier.padding(horizontal = spacingLg),
                            text = "Crédit Agricole",
                            color = CA_Gray_500,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                items(state.caBanks, key = { it.name }) { bank ->
                    BankCard(bank = bank, openOperationsDetail = openOperationsDetail)
                }

                item {
                    if (state.otherBanks.isNotEmpty()) {
                        Text(
                            modifier = Modifier.padding(horizontal = spacingLg),
                            text = "Autres banques",
                            color = CA_Gray_500,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                items(state.otherBanks, key = { it.name }) { bank ->
                    BankCard(bank = bank, openOperationsDetail = openOperationsDetail)
                }
            }
        }
    }
}

@Composable
fun AccountsTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Mes comptes",
        style = MaterialTheme.typography.headlineLarge,
        color = CA_Black,
        modifier = modifier.padding(horizontal = spacingLg, vertical = spacingLg)
    )
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(modifier: Modifier = Modifier, error: String) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Erreur: $error")
    }
}


@Composable
private fun BankCard(bank: BankUi, modifier: Modifier = Modifier, openOperationsDetail: (id: String) -> Unit) {
    var expand by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(if (expand) 270f else 90f) }

    LaunchedEffect(expand) {
        rotation.animateTo(
            targetValue = if (expand) 270f else 90f,
            animationSpec = tween(500, easing = LinearEasing)
        )
    }

    Column(modifier = modifier.fillMaxWidth().background(color = Color.White)) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = spacingMd, horizontal = spacingXl)
                .clickable {
                    expand = !expand
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                bank.name,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleSmall
            )
            Text(bank.totalText, style = MaterialTheme.typography.bodyMedium, color = CA_Gray_300)

            Icon(
                modifier = Modifier
                    .rotate(rotation.value)
                    .size(48.dp),
                tint = CA_Gray_300,
                imageVector = Icons.AutoMirrored.Filled.ArrowRight, contentDescription = null
            )
        }

        HorizontalDivider(color = CA_Gray_100)

        if (expand) {
            bank.accounts.forEach { account ->
                AccountRowCompact(
                    account = account,
                    onClick = openOperationsDetail
                )
            }
        }
    }
}

@Composable
private fun AccountRowCompact(
    account: AccountUi,
    onClick: (accountId: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(account.id) }
            .padding(
                start = spacing6XL,
                top = spacingSm,
                bottom = spacingSm,
                end = spacingLg
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = account.label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = account.balanceText,
            style = MaterialTheme.typography.bodyMedium,
            color = CA_Gray_300
        )

        Icon(
            modifier = Modifier.size(24.dp),
            tint = CA_Gray_300,
            imageVector = Icons.AutoMirrored.Filled.ArrowRight,
            contentDescription = null
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(start = spacing6XL),
        color = CA_Gray_100
    )
}
