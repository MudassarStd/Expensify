package com.std.composeexpensetracker.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.std.composeexpensetracker.ui.feature.add.AddIncomeExpenseScreen
import com.std.composeexpensetracker.ui.feature.home.HomeScreen
import com.std.composeexpensetracker.ui.feature.details.TransactionDetailsScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController() // so it can remember nav-stack across config changes

    // navController controls screen stack (push/pop/back stacking)
    // navHost maintains screen stack over here
    NavHost(navController= navController, startDestination = ScreenRoute.MainScreen.route) {
        // list all possible routes here
        composable(ScreenRoute.MainScreen.route) { HomeScreen(navController = navController) }
        composable(ScreenRoute.DetailsScreen.route) { TransactionDetailsScreen(navController = navController) }
        composable(ScreenRoute.AddTransactionScreen.route) { AddIncomeExpenseScreen(navController = navController) }
    }
}