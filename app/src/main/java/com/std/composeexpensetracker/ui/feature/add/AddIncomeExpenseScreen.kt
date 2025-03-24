package com.std.composeexpensetracker.ui.feature.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.std.composeexpensetracker.R
import com.std.composeexpensetracker.data.repository.MainRepositoryImpl
import com.std.composeexpensetracker.ui.components.TopRowHeader
import com.std.composeexpensetracker.ui.feature.MainViewModel
import com.std.composeexpensetracker.ui.theme.Zinc
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AddIncomeExpenseScreen(modifier: Modifier = Modifier, navController: NavController, viewmodel: MainViewModel) {

    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    ConstraintLayout(modifier = modifier
        .fillMaxSize()
        .systemBarsPadding()) {
        // create refs
        val (bg, topRow, dataFormCard) = createRefs()
        Image(
            painter = painterResource(R.drawable.top_bg),
            contentDescription = null,
            modifier = Modifier.constrainAs(bg) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        TopRowHeader (modifier = Modifier
            .constrainAs(topRow) {
                top.linkTo(parent.top)
            }, title = "Add Expense", navController = navController)


        DataFormCard(modifier = Modifier.constrainAs(dataFormCard) {
            top.linkTo(bg.bottom)
            bottom.linkTo(bg.bottom)
        }, viewmodel, selectedDate) {
            showDatePicker = true
        }

        if (showDatePicker) {
            DatePickerExample(onDateSelected = { selectedDate = it }) {
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
    Card (
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .height(400.dp)
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 24.dp))
    {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp))

            Text("Amount")
            OutlinedTextField(
                value = transactionState.amount.toString(),

                onValueChange = {
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
                    .height(50.dp))

            Text("Date")
            OutlinedTextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                    )
                },
                placeholder = {
                    Text("$selectedDate")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable { showDatePicker() })

            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = {
                viewModel.addTransaction() // reset state by passing default state
                // add transaction
                // clear fields (could be done by resetting transactionState)
            }, modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = null, tint = Zinc)

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
    var dateSelected: String = ""

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedButton(onClick = {}) {
            Text("Select Date")
        }

        // Show selected date
        datePickerState.selectedDateMillis?.let { millis ->
            dateSelected = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(millis))
            Text("Selected Date: ${dateSelected}")
        }
    }
        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    onDateSelected(dateSelected)
                    onDismiss()
                }) {
                    Text("OK")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }

}


@Preview (showBackground = true)
@Composable
private fun AddIncomeExpensePreview() {
    AddIncomeExpenseScreen(navController = rememberNavController(), viewmodel = koinViewModel())
}