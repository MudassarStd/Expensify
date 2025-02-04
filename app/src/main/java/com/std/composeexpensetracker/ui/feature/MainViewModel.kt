package com.std.composeexpensetracker.ui.feature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.data.repository.MainRepositoryImpl
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepositoryImpl
): ViewModel() {

    // state management here
    private val _transactionState = mutableStateOf(Transaction())
    val transactionState: State<Transaction> get() = _transactionState // read-only state

    fun updateTransactionState(updatedState: Transaction) {
        _transactionState.value = updatedState
    }

    // handle add event
    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            mainRepository.add(transaction)
            _transactionState.value = Transaction()  // reset state
        }
    }


    fun saveUser(name: String, age: String) {

    }

}