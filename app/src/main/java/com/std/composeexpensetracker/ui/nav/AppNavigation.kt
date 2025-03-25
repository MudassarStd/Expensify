package com.std.composeexpensetracker.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.std.composeexpensetracker.ui.feature.MainViewModel
import com.std.composeexpensetracker.ui.feature.add.AddIncomeExpenseScreen
import com.std.composeexpensetracker.ui.feature.home.HomeScreen
import com.std.composeexpensetracker.ui.feature.details.TransactionDetailsScreen
import com.std.composeexpensetracker.ui.feature.transactions.TransactionsScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun AppNavigation(viewModel: MainViewModel = koinViewModel()) {

    val navController = rememberNavController() // so it can remember nav-stack across config changes

    // navController controls screen stack (push/pop/back stacking)
    // navHost maintains screen stack over here
    NavHost(navController= navController, startDestination = ScreenRoute.MainScreen.route) {
        // list all possible routes here
        composable(ScreenRoute.MainScreen.route) { HomeScreen(navController = navController, viewmodel = viewModel) }
        composable(ScreenRoute.DetailsScreen.route) { TransactionDetailsScreen(navController = navController, viewmodel = viewModel) }
        composable(ScreenRoute.AddTransactionScreen.route) { AddIncomeExpenseScreen(navController = navController, viewmodel = viewModel) }
        composable(ScreenRoute.TransactionsScreen.route) { TransactionsScreen(navController = navController, viewModel = viewModel) }
    }
}