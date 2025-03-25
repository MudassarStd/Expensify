package com.std.composeexpensetracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.data.local.model.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun add(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("select sum(amount) from `Transaction`")
    fun getTotalAmount(): Flow<Double>

    @Query("select sum(amount) from `Transaction` where type = :transactionType")
    fun getAmountByTransactionType(transactionType: TransactionType): Flow<Double>

    @Query("select * from `Transaction`")
    fun getAll(): Flow<List<Transaction>>

    @Query("select * from `Transaction` order by id desc limit 4")
    fun getRecentTransactions(): Flow<List<Transaction>>
}