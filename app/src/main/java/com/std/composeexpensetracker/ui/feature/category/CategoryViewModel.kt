package com.std.composeexpensetracker.ui.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.std.composeexpensetracker.data.local.model.Category
import com.std.composeexpensetracker.data.repository.MainRepository
import com.std.composeexpensetracker.di.viewModelModule
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val mainRepository: MainRepository
): ViewModel() {

    fun add(name: String) = viewModelScope.launch { mainRepository.addCategory(Category(name = name)) }
    fun delete(category: Category) = viewModelScope.launch { mainRepository.deleteCategory(category) }

    val categories: StateFlow<List<Category>> = mainRepository.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

}