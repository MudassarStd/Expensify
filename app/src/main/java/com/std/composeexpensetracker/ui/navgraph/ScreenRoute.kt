package com.std.composeexpensetracker.ui.navgraph


// sealed class is like an advanced enum
sealed class ScreenRoute(val route: String) {
    data object MainScreen: ScreenRoute("main")
    object DetailsScreen: ScreenRoute("details")
    object AddTransactionScreen: ScreenRoute("addTransaction")
}


