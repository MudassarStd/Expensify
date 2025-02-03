package com.std.composeexpensetracker.data.local.model

import androidx.room.Entity


@Entity
data class Transaction(
    val id: Int = 0,
    val type: TransactionType,
    val category: String,
    val amount: Double,
    val date: Long = System.currentTimeMillis(),

    )

enum class TransactionType {
    INCOME,
    EXPENSE
}
