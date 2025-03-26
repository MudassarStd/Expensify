package com.std.composeexpensetracker.ui.feature.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheetDefaults.properties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.std.composeexpensetracker.data.local.model.Category
import com.std.composeexpensetracker.ui.components.TopRowHeader
import com.std.composeexpensetracker.ui.feature.transactions.categories
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryScreen(
    navController: NavController,
    viewModel: CategoryViewModel = koinViewModel()
) {
    var showAdditionDialog by remember { mutableStateOf(false) }

    val categories by viewModel.categories.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        ExtendedFloatingActionButton(onClick = { showAdditionDialog = true }) {
            Text("Add Category")
        }
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TopRowHeader(title = "Select Category", showTrailingIcon = false, onLeadClick = { navController.popBackStack() })

            LazyColumn(contentPadding = PaddingValues(horizontal = 12.dp)) {
                items(categories) { category ->
                    Category(category) {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("selectedItem", category)
                        navController.popBackStack()
                    }
                }
            }
            if (categories.isEmpty()) {
                CircularProgressIndicator()
            }

            if (showAdditionDialog) {
                AddCategoryDialog(onAddCategory = { category -> viewModel.add(category) }) {
                    showAdditionDialog = false
                }
            }
        }
    }
}

@Composable
fun AddCategoryDialog(
    onAddCategory: (String) -> Unit,
    onDismiss: () -> Unit
) {

    var name by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(12.dp).background(color = Color.White)) {
            Text("Add Category", fontWeight = FontWeight.Bold)

            OutlinedTextField(value = name, onValueChange = { name = it }, placeholder = { Text("Category Name") })

            TextButton(onClick = {
                onAddCategory(name)
                onDismiss()
            }) {
                Text("Add")
            }
        }
    }
}

@Composable
fun Category(
    category: Category,
    onSelectCategory: () -> Unit
) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            Text(category.name, modifier = Modifier.weight(1f))
            TextButton(onClick = onSelectCategory) {
                Text("Select")
            }
        }
}