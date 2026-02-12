package com.example.kmpmobileca.ui.screen.operations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpmobileca.domain.model.Operation
import com.example.kmpmobileca.ui.theme.CA_Gray_100
import com.example.kmpmobileca.ui.theme.spacingLg
import com.example.kmpmobileca.ui.utils.formatDate
import com.example.kmpmobileca.ui.utils.formatEuro
import io.ktor.websocket.Frame
import org.koin.compose.koinInject

@Composable
fun OperationsDetailScreen(
    accountId: String,
    viewModel: OperationsDetailsViewModel = koinInject(),
    onBack: () -> Unit
) {

    /* ------------------------------------- */
    /*              State                    */
    /* ------------------------------------- */
    val state by viewModel.state.collectAsState()

    /* ------------------------------------- */
    /*              LaunchedEffect           */
    /* ------------------------------------- */

    LaunchedEffect(Unit) {
        viewModel.getOperations(accountId = accountId)
    }

    when {
        state.isLoading -> {
            Loading()
        }

        state.error != null -> {
            Error(error = state.error.orEmpty())
        }

        else -> {
            Column(Modifier.fillMaxSize()) {

                BackButton(onBack = onBack)

                AccountHeader(balance = state.balance, label = state.accountLabel)

                LazyColumn(
                    contentPadding = PaddingValues(horizontal = spacingLg),
                ) {
                    items(state.operations) { op ->
                        OperationRow(op)
                    }
                }
            }

        }
    }


}

@Composable
fun BackButton(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onBack)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}


@Composable
fun OperationRow(operation: Operation) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = operation.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = formatDate(operation.date),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = formatEuro(operation.amount),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    HorizontalDivider(thickness = 0.5.dp, color = CA_Gray_100)
}


@Composable
private fun AccountHeader(
    balance: Double,
    label: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp, bottom = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatEuro(balance),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Error(modifier: Modifier = Modifier, error: String) {
    Box(modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
        Frame.Text("Erreur: $error")
    }
}