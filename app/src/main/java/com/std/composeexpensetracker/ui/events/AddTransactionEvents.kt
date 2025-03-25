package com.std.composeexpensetracker.ui.events

import com.std.composeexpensetracker.data.local.model.TransactionType

sealed class AddTransactionEvents {
    data class OnAddCategory(val category: String): AddTransactionEvents()
    data class OnAddAmount(val amount: String): AddTransactionEvents()
    data class OnAddTransactionType(val type: TransactionType): AddTransactionEvents()
}