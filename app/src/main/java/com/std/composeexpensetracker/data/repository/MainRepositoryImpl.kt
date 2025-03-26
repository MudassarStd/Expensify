package com.std.composeexpensetracker.data.repository

import androidx.lifecycle.LiveData
import androidx.room.PrimaryKey
import com.std.composeexpensetracker.data.local.CategoryDao
import com.std.composeexpensetracker.data.local.TransactionDao
import com.std.composeexpensetracker.data.local.model.Category
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.data.local.model.TransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet

class MainRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val categoryDao: CategoryDao
    ): MainRepository {

    override suspend fun add(transaction: Transaction) {
        transactionDao.add(transaction)
    }

    override suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction)
    }

    override suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction)
    }

    override fun getTotalAmount(): Flow<Double> {
        return transactionDao.getTotalAmount()
    }

    override fun getAmountByTransactionType(transactionType: TransactionType): Flow<Double> {
        return transactionDao.getAmountByTransactionType(transactionType)
    }

    override fun getAll(): Flow<List<Transaction>> = transactionDao.getAll()

    override fun getRecentTransactions(): Flow<List<Transaction>>  = transactionDao.getRecentTransactions()


    // category ops
    override suspend fun addCategory(category: Category) = categoryDao.add(category)
    override suspend fun deleteCategory(category: Category) = categoryDao.delete(category)
    override fun getAllCategories(): Flow<List<Category>> = categoryDao.getAll()
}