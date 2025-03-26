package com.std.composeexpensetracker.ui.feature.transactions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.data.repository.MainRepository
import com.std.composeexpensetracker.ui.state.FilterModalState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TransactionsViewModel(
    private val mainRepository: MainRepository
): ViewModel() {

    private var _filterModalState = mutableStateOf(FilterModalState())
    val filterModalState: State<FilterModalState> = _filterModalState

    val transactions: StateFlow<List<Transaction>> = mainRepository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    fun updateFilterModalState(state: FilterModalState) {
        _filterModalState.value = state
    }

    fun resetFilterState() { _filterModalState.value = FilterModalState() }

    // filter logic
}

