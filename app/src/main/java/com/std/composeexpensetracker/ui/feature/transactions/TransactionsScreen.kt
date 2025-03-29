package com.std.composeexpensetracker.ui.feature.transactions

import android.media.tv.TvContract.Channels.Logo
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.std.composeexpensetracker.data.local.model.TransactionType
import com.std.composeexpensetracker.ui.components.TopRowHeader
import com.std.composeexpensetracker.ui.feature.MainViewModel
import com.std.composeexpensetracker.ui.feature.add.DatePickerExample
import com.std.composeexpensetracker.ui.feature.category.CategoryViewModel
import com.std.composeexpensetracker.ui.feature.home.TransactionItem
import com.std.composeexpensetracker.ui.state.AmountOrder
import com.std.composeexpensetracker.util.DateTimeUtils
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TransactionsViewModel = koinViewModel()
) {

    val transactions by viewModel.transactions.collectAsStateWithLifecycle()

    var showFilterSheetModal by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopRowHeader(
            title = "Transactions",
            showTrailingIcon = true,
            trailingIcon = Icons.Default.Favorite,
            onLeadClick = { navController.popBackStack() },
            onTrailClick = { showFilterSheetModal = true }
        )

        LazyColumn {
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }

        if (showFilterSheetModal) {
            FilterSheetModal(viewModel = viewModel, onDismiss = { showFilterSheetModal = false })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSheetModal(
    viewModel: TransactionsViewModel,
    categoryViewModel: CategoryViewModel = koinViewModel(),
    onDismiss: () -> Unit
) {

    val filterModalState by viewModel.filterModalState.collectAsStateWithLifecycle()
    val categories by categoryViewModel.categories.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var showDatePicker by remember { mutableStateOf(false) }
    var selectedStartDate by remember { mutableStateOf("Start Date") }
    var selectedEndDate by remember { mutableStateOf("End Date") }

    var isStartDate by remember { mutableStateOf(false) }
    var startDate by remember { mutableLongStateOf(0L) }

    var invalidDateRange by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerExample(onDateSelected = { dateLong ->
            val date = DateTimeUtils.formatDate(dateLong)
            if (isStartDate) {
                selectedStartDate = date
                startDate = dateLong
                viewModel.filterEvent(FilterEvent.StartDate(dateLong))
                Log.d("TestingStartDate", "isStartDate")
            } else {
                selectedEndDate = date
                if (startDate < dateLong) {
                    viewModel.filterEvent(FilterEvent.EndDate(dateLong))
                    Log.d("TestingStartDate", "isEndDate")
                    invalidDateRange = false
                } else {
                    invalidDateRange = true
                }
            }
        }) {
            showDatePicker = false
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
            scope.launch { sheetState.hide() }
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Filter Transactions", fontWeight = FontWeight.Bold)
                TextButton(onClick = {
                    viewModel.filterEvent(FilterEvent.Reset)
                    invalidDateRange = false
                    selectedStartDate = "Start Date"
                    selectedEndDate = "End Date"
                }) {
                    Text("Reset")
                }
            }
            Text("Date", fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AssistChip(
                    onClick = {
                        isStartDate = true
                        showDatePicker = true
                    },
                    label = { Text(selectedStartDate) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                    }
                )
                Text("to")
                AssistChip(
                    onClick = {
                        isStartDate = false
                        showDatePicker = true
                    },
                    label = { Text(selectedEndDate) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                    }
                )
            }

            if (invalidDateRange) {
                Text("Start date should be less than end date", color = Color.Red, fontSize = 12.sp)
            }

            Text("Transaction Type", fontWeight = FontWeight.Bold)
            Row {
                FilterChip(
                    selected = filterModalState.type == TransactionType.ALL,
                    onClick = { viewModel.filterEvent(FilterEvent.Type(TransactionType.ALL)) },
                    label = { Text("All") })
                Spacer(Modifier.width(12.dp))
                FilterChip(
                    selected = filterModalState.type == TransactionType.INCOME,
                    onClick = { viewModel.filterEvent(FilterEvent.Type(TransactionType.INCOME)) },
                    label = { Text("Income") })
                Spacer(Modifier.width(12.dp))
                FilterChip(
                    selected = filterModalState.type == TransactionType.EXPENSE,
                    onClick = { viewModel.filterEvent(FilterEvent.Type(TransactionType.EXPENSE)) },
                    label = { Text("Expense") })
            }
            Text("Amount", fontWeight = FontWeight.Bold)

            Row {
                FilterChip(
                    selected = filterModalState.amountOrder == AmountOrder.Highest,
                    onClick = { viewModel.filterEvent(FilterEvent.Order(AmountOrder.Highest)) },
                    label = { Text("Highest") })
                Spacer(Modifier.width(12.dp))
                FilterChip(
                    selected = filterModalState.amountOrder == AmountOrder.Lowest,
                    onClick = { viewModel.filterEvent(FilterEvent.Order(AmountOrder.Lowest)) },
                    label = { Text("Lowest") })
            }

            Text("Category", fontWeight = FontWeight.Bold)

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    FilterChip(
                        onClick = {
                            viewModel.filterEvent(FilterEvent.AddCategory(category.name))
                        },
                        label = { Text(category.name) },
                        selected = filterModalState.selectedCategory.contains(category)
                    )
                }
            }
            if (categories.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            OutlinedButton(
                onClick = {
                    onDismiss()
                    scope.launch { sheetState.hide() }
                }, modifier = Modifier.fillMaxWidth()
            ) { Text("Apply Filters") }
        }
    }
}

//val categories = emptyList<String>()
val categories = listOf(
    "Salary",
    "Freelance Work",
    "Investments",
    "Business Revenue",
    "Rental Income",
    "Groceries",
    "Rent/Mortgage",
    "Utilities",
    "Entertainment",
    "Transportation"
)