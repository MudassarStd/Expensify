package com.std.composeexpensetracker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: TransactionType = TransactionType.INCOME,
    val category: String = "",
    val amount: Double = 0.0,
    val date: Long = 0L,
)

enum class TransactionType {
    INCOME,
    EXPENSE,
    ALL
}
