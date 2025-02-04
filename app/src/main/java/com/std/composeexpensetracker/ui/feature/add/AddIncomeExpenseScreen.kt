package com.std.composeexpensetracker.ui.feature.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.std.composeexpensetracker.R
import com.std.composeexpensetracker.data.local.model.Transaction
import com.std.composeexpensetracker.ui.components.TopRowHeader
import com.std.composeexpensetracker.ui.feature.MainViewModel
import com.std.composeexpensetracker.ui.feature.home.TransactionItem
import com.std.composeexpensetracker.ui.theme.Zinc
import org.koin.androidx.compose.getViewModel

/* Planning
* Using constraint layout
* bgImage
* top row
* then we have data form card in center overlaid on bg_top_image
* */


@Composable
fun AddIncomeExpenseScreen(modifier: Modifier = Modifier, navController: NavController) {

    val viewmodel: MainViewModel = getViewModel()

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
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
        }, viewmodel)
    }
}


@Composable
fun DataFormCard(modifier: Modifier = Modifier, viewModel: MainViewModel) {

    val transactionState by viewModel.transactionState // default transaction state (internal state of Data form comp

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
                modifier = Modifier.fillMaxWidth().height(50.dp))

            Text("Amount")
            OutlinedTextField(
                value = transactionState.amount,
                onValueChange = { viewModel.updateTransactionState(transactionState.copy(amount = it)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = null,
                    )
                },
                placeholder = {
                    Text("$48.00")
                },
                modifier = Modifier.fillMaxWidth().height(50.dp))

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
                    Text("Tue, 22 Feb 2025")
                },
                modifier = Modifier.fillMaxWidth().height(50.dp))

            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = {
                viewModel.addTransaction(transactionState) // reset state by passing default state
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

@Preview (showBackground = true)
@Composable
private fun AddIncomeExpensePreview() {
//    AddIncomeExpenseScreen()
}