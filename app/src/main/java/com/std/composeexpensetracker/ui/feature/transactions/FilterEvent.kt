package com.std.composeexpensetracker.ui.feature.transactions

import com.std.composeexpensetracker.data.local.model.TransactionType
import com.std.composeexpensetracker.ui.state.AmountOrder

sealed class FilterEvent {
    data class StartDate(val date: Long): FilterEvent()
    data class EndDate(val date: Long): FilterEvent()
    data class Type(val type: TransactionType): FilterEvent()
    data class Order(val order: AmountOrder): FilterEvent()
    data class AddCategory(val category: String): FilterEvent()

    data object Reset: FilterEvent()
    data object Apply: FilterEvent()
}