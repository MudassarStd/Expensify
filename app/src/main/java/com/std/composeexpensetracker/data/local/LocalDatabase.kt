package com.std.composeexpensetracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.std.composeexpensetracker.data.local.model.Category
import com.std.composeexpensetracker.data.local.model.Transaction


@Database(entities = [Transaction::class, Category::class], version = 1, exportSchema = false)
@TypeConverters(DateStringConverter::class)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile // Ensures changes made by one thread are immediately visible to other threads.
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "app_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}