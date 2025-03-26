package com.std.composeexpensetracker.ui.state

import com.std.composeexpensetracker.data.local.model.TransactionType

data class FilterModalState(
    val startDate: Long = System.currentTimeMillis(),
    val endDate: Long = System.currentTimeMillis(),
    val type: TransactionType = TransactionType.INCOME,
    val amountOrder: AmountOrder = AmountOrder.Highest,
    val category: List<String> = emptyList()
)

enum class AmountOrder { Highest, Lowest }