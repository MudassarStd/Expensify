package com.std.composeexpensetracker.data.repository

import androidx.lifecycle.LiveData
import com.std.composeexpensetracker.data.local.model.Transaction

interface MainRepository {
    suspend fun add(transaction: Transaction)
    suspend fun update(transaction: Transaction)
    suspend fun delete(transaction: Transaction)
    fun getAll(): LiveData<List<Transaction>>
}