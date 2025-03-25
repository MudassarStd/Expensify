package com.std.composeexpensetracker.ui.feature.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.std.composeexpensetracker.R
import com.std.composeexpensetracker.data.local.model.TransactionType
import com.std.composeexpensetracker.data.repository.MainRepositoryImpl
import com.std.composeexpensetracker.ui.components.TopRowHeader
import com.std.composeexpensetracker.ui.events.AddTransactionEvents
import com.std.composeexpensetracker.ui.feature.MainViewModel
import com.std.composeexpensetracker.ui.theme.Zinc
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AddIncomeExpenseScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewmodel: MainViewModel
) {

    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val (bg, topRow, dataFormCard) = createRefs()

        // create refs
        Image(
            painter = painterResource(R.drawable.top_bg),
            contentDescription = null,
            modifier = Modifier.constrainAs(bg) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        TopRowHeader(
            modifier = Modifier
                .constrainAs(topRow) {
                    top.linkTo(parent.top)
                }, title = "Add Expense",
            showTrailingIcon = false,
            onLeadClick = { navController.popBackStack() }
        )


        DataFormCard(modifier = Modifier.constrainAs(dataFormCard) {
            top.linkTo(topRow.bottom)
        }, viewmodel, selectedDate) {
            showDatePicker = true
        }

        if (showDatePicker) {
            DatePickerExample(onDateSelected = {
                selectedDate = it
                viewmodel.addDate(selectedDate)
                showDatePicker = false
            }) {
                showDatePicker = false
            }
        }
    }
}

@Composable
fun DataFormCard(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    selectedDate: String,
    showDatePicker: () -> Unit
) {

    val transactionState by viewModel.transactionUIState
    var amountText by remember { mutableStateOf("") }
    var selectedTransactionType by remember { mutableStateOf(TransactionType.INCOME) }

    Card(
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 24.dp)
    )
    {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Transaction Type")
            Row {
                FilterChip(selected = selectedTransactionType == TransactionType.INCOME, onClick = {
                    selectedTransactionType = TransactionType.INCOME
                    viewModel.updateTransactionState(transactionState.copy(type = selectedTransactionType))
                }, label = { Text("Income") })
                Spacer(Modifier.width(12.dp))
                FilterChip(
                    selected = selectedTransactionType == TransactionType.EXPENSE,
                    onClick = {
                        selectedTransactionType = TransactionType.EXPENSE
                        viewModel.updateTransactionState(transactionState.copy(type = selectedTransactionType))
                    },
                    label = { Text("Expense") })
            }
            Text("Category")
            OutlinedTextField(
                value = transactionState.category,
                onValueChange = { viewModel.updateTransactionState(transactionState.copy(category = it)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                    )
                },
                placeholder = {
                    Text("Netflix")
                },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null,
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Text("Amount")
            OutlinedTextField(
                value = amountText,
                onValueChange = {
                    amountText = it
                    val amount = it.toDoubleOrNull() ?: 0.0
                    viewModel.updateTransactionState(transactionState.copy(amount = amount))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = null,
                    )
                },
                placeholder = {
                    Text("$48.00")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Text("Date")
            OutlinedTextField(
                value = selectedDate,
                readOnly = true,
                onValueChange = {
                    // add date to viewModel here
                },
                leadingIcon = {
                    IconButton(onClick = { showDatePicker() }) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = null,
                        )
                    }
                },
                placeholder = {
                    Text("14 Mar 2025")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = {
                viewModel.addTransaction()
                amountText = "" // reset local state
            }, modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null, tint = Zinc
                )

                Text("Add Transaction", color = Zinc)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerExample(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    var dateSelected by remember { mutableStateOf("") }

    if (datePickerState.selectedDateMillis != null) {
        dateSelected = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            .format(Date(datePickerState.selectedDateMillis!!))
    }

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (dateSelected.isNotEmpty()) {
                    onDateSelected(dateSelected)
                }
                onDismiss()
            }) {
                Text("Ok")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview(showBackground = true)
@Composable
private fun AddIncomeExpensePreview() {
    AddIncomeExpenseScreen(navController = rememberNavController(), viewmodel = koinViewModel())
}