package com.std.composeexpensetracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.std.composeexpensetracker.data.local.model.Category
import com.std.composeexpensetracker.data.local.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("select * from `Category`")
    fun getAll(): Flow<List<Category>>
}