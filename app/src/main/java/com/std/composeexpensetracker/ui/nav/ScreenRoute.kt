package com.std.composeexpensetracker.ui.nav

// sealed class is just like an advanced enum
sealed class ScreenRoute(val route: String) {
    data object MainScreen: ScreenRoute("main")
    data object DetailsScreen: ScreenRoute("details")
    data object AddTransactionScreen: ScreenRoute("addTransaction")
}