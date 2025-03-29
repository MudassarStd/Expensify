package com.std.composeexpensetracker.ui.state

import com.std.composeexpensetracker.data.local.model.Category
import com.std.composeexpensetracker.data.local.model.TransactionType

data class FilterModalState(
    val startDate: Long = System.currentTimeMillis(),
    val endDate: Long = System.currentTimeMillis(),
    val type: TransactionType = TransactionType.ALL,
    val amountOrder: AmountOrder = AmountOrder.Highest ,
    var selectedCategory: MutableList<Category> = mutableListOf()
)

enum class AmountOrder { Highest, Lowest }