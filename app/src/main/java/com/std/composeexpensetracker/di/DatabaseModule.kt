package com.std.composeexpensetracker.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.std.composeexpensetracker.data.local.LocalDatabase
import com.std.composeexpensetracker.data.local.TransactionDao
import com.std.composeexpensetracker.data.repository.MainRepository
import com.std.composeexpensetracker.data.repository.MainRepositoryImpl
import com.std.composeexpensetracker.ui.feature.MainViewModel
import org.koin.androidx.compose.get
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {

    single { LocalDatabase.getDatabase(get()) } // provides database instance

    // single provides singleton
    // get() asks Koin for required dependencies (to create object)
    // in this case, getdatabase() requires app context, koin will provide that to get() call here

    single { get<LocalDatabase>().transactionDao() } // providers transactionDao()

    single { MainRepositoryImpl(get()) } bind MainRepository::class
    // provides main repository singleton

    viewModel { MainViewModel(get()) }
}