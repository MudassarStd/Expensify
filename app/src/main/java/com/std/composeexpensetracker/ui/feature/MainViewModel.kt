package com.std.composeexpensetracker.ui.feature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val transactions: StateFlow<List<Transaction>> = mainRepository.getAll()
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
        _transactionUIState.value = Transaction() // reset state
    }

    fun addTransaction() = viewModelScope.launch {
        mainRepository.add(transactionUIState.value)
    }
}