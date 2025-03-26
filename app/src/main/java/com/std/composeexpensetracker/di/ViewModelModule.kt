package com.std.composeexpensetracker.di

import com.std.composeexpensetracker.ui.feature.MainViewModel
import com.std.composeexpensetracker.ui.feature.category.CategoryViewModel
import com.std.composeexpensetracker.ui.feature.transactions.TransactionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule  = module {
    viewModel { MainViewModel(get()) }
    viewModel { TransactionsViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
}