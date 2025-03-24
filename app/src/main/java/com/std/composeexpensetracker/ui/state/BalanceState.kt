package com.std.composeexpensetracker.ui.state

data class BalanceState(
    val totalAmount: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0
)