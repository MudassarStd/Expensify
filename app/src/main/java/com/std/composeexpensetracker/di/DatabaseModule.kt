package com.std.composeexpensetracker.di

import com.std.composeexpensetracker.data.local.LocalDatabase
import com.std.composeexpensetracker.data.local.TransactionDao
import org.koin.dsl.module

val databaseModule = module {

    single { LocalDatabase.getDatabase(get()) }

    // single provides singleton
    // get() asks Koin for required dependencies
    // in this case, getdatabase() requires app context, koin will provide that to get() call here


    single { get<LocalDatabase>().transactionDao() }

    // get instance of LocalDatabase first to call transactionDao()
}