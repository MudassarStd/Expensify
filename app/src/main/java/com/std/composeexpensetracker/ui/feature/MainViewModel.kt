package com.std.composeexpensetracker.ui.feature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.data.local.model.TransactionType
import com.std.composeexpensetracker.data.repository.MainRepository
import com.std.composeexpensetracker.ui.state.BalanceState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository

): ViewModel() {
    private val _transactionUIState = mutableStateOf(Transaction())
    val transactionUIState: State<Transaction> = _transactionUIState



    val recentTransactions: StateFlow<List<Transaction>> = mainRepository.getRecentTransactions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val balanceState: StateFlow<BalanceState> = combine(
        mainRepository.getTotalAmount(),
        mainRepository.getAmountByTransactionType(TransactionType.INCOME),
        mainRepository.getAmountByTransactionType(TransactionType.EXPENSE)
    ) { total, income, expense ->
        BalanceState(total, income, expense)
    }.stateIn(viewModelScope, SharingStarted.Lazily, BalanceState())

    fun updateTransactionState(transaction: Transaction) {
        _transactionUIState.value = transaction
    }

    fun addDate(date: Long) {
        _transactionUIState.value = _transactionUIState.value.copy(date = date)
    }

    fun addTransaction() = viewModelScope.launch {
        mainRepository.add(transactionUIState.value)
        _transactionUIState.value = Transaction() // resets transaction state
    }
}