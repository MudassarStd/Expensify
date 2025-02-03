package com.std.composeexpensetracker.data.repository

import androidx.lifecycle.LiveData
import com.std.composeexpensetracker.data.local.TransactionDao
import com.std.composeexpensetracker.data.local.model.Transaction

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

    override fun getAll(): LiveData<List<Transaction>> = transactionDao.getAll()
}