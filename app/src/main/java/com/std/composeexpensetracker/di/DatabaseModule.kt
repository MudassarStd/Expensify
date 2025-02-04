package com.std.composeexpensetracker.di

import com.std.composeexpensetracker.data.local.LocalDatabase
import com.std.composeexpensetracker.data.local.TransactionDao
import com.std.composeexpensetracker.data.repository.MainRepository
import com.std.composeexpensetracker.data.repository.MainRepositoryImpl
import com.std.composeexpensetracker.ui.feature.MainViewModel
import org.koin.dsl.module

val databaseModule = module {

    single { LocalDatabase.getDatabase(get()) } // provides database instance

    // single provides singleton
    // get() asks Koin for required dependencies (to create object)
    // in this case, getdatabase() requires app context, koin will provide that to get() call here


    single { get<LocalDatabase>().transactionDao() } // providers transactionDao()


    single { MainRepositoryImpl(get()) }  // provides main repository singleton

    single { MainViewModel(get()) }

    // get instance of LocalDatabase first to call transactionDao()
}