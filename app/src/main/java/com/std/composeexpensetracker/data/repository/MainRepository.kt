package com.std.composeexpensetracker.data.repository

import androidx.lifecycle.LiveData
import com.std.composeexpensetracker.data.local.model.Category
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.data.local.model.TransactionType
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun add(transaction: Transaction)
    suspend fun update(transaction: Transaction)
    suspend fun delete(transaction: Transaction)

    fun getTotalAmount(): Flow<Double>
    fun getAmountByTransactionType(transactionType: TransactionType): Flow<Double>

    fun getAll(): Flow<List<Transaction>>
    fun getRecentTransactions(): Flow<List<Transaction>>

    // category
    suspend fun addCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    fun getAllCategories(): Flow<List<Category>>
}