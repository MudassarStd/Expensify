package com.std.composeexpensetracker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.std.composeexpensetracker.data.local.model.Transaction

@Dao
interface TransactionDao {
    @Insert
    suspend fun add(transaction: Transaction)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("select * from `Transaction`")
    fun getAll(): LiveData<List<Transaction>>

}