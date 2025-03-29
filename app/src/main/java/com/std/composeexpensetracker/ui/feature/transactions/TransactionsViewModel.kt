package com.std.composeexpensetracker.ui.feature.transactions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.data.repository.MainRepository
import com.std.composeexpensetracker.ui.state.FilterModalState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class TransactionsViewModel(
    private val mainRepository: MainRepository
): ViewModel() {

    private var _filterModalState = MutableStateFlow(FilterModalState())
    val filterModalState: StateFlow<FilterModalState> = _filterModalState.asStateFlow()

    val selectedCategories = mutableListOf<String>()

    val transactions: StateFlow<List<Transaction>> = mainRepository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    fun filterEvent(event: FilterEvent) {
        when(event) {
            is FilterEvent.StartDate -> {
                _filterModalState.value = _filterModalState.value.copy(startDate = event.date)
            }
            is FilterEvent.EndDate -> {
                _filterModalState.value = _filterModalState.value.copy(endDate = event.date)
            }
            is FilterEvent.Order -> {
                _filterModalState.value = _filterModalState.value.copy(amountOrder = event.order)
            }
            is FilterEvent.AddCategory -> {
                if (!selectedCategories.contains(event.category)) {
                    selectedCategories.add(event.category)
                } else {
                    selectedCategories.remove(event.category)
                }
            }
            is FilterEvent.Type -> {
                _filterModalState.value = _filterModalState.value.copy(type = event.type)
            }
            FilterEvent.Reset -> {
                _filterModalState.value = FilterModalState()
            }
            FilterEvent.Apply -> TODO()
        }
    }

    // filter logic
}

