package com.std.composeexpensetracker.ui.feature.transactions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.std.composeexpensetracker.ui.components.TopRowHeader
import com.std.composeexpensetracker.ui.feature.MainViewModel
import com.std.composeexpensetracker.ui.feature.home.TransactionItem

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {

    val transactions by viewModel.transactions.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TopRowHeader(
            title = "Transactions",
            showTrailingIcon = true,
            trailingIcon = Icons.Default.Favorite,
            onLeadClick = { navController.popBackStack() }
        )

        LazyColumn {
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}