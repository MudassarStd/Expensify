package com.std.composeexpensetracker.data.repository

import androidx.lifecycle.LiveData
import com.std.composeexpensetracker.data.local.TransactionDao
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.data.local.model.TransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet

class MainRepositoryImpl(
    private val transactionDao: TransactionDao
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
}