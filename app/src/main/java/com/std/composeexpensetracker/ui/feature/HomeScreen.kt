package com.std.composeexpensetracker.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.std.composeexpensetracker.R
import com.std.composeexpensetracker.ui.theme.Zinc


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
        Surface(modifier = modifier.fillMaxSize()) {
            ConstraintLayout {
                val (topRow, card, transactionList, bgImage, listHeader) = createRefs()
                Image(
                    painter = painterResource(R.drawable.top_bg),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(bgImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                Box (modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp)
                    .constrainAs(topRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Column {
                        Text("Good morning,", color = Color.White)
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Mudassar",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp)
                    }

                    IconButton(
                        onClick = {},
                        modifier = Modifier.align(alignment = Alignment.CenterEnd)) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                    }
                }

                MainCardView(modifier = Modifier
                    .constrainAs(card) {
                        top.linkTo(topRow.bottom)
                    }
                    .padding(top = 24.dp))

                // transaction list header
                Row(modifier = Modifier
                    .constrainAs(listHeader) {
                        top.linkTo(card.bottom)
                    }
                    .padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Transaction History", modifier = Modifier.weight(1f))
                    TextButton(onClick = {}) { Text("See all") }
                }

                // transaction list
                LazyColumn(modifier = Modifier.constrainAs(transactionList) {
                    top.linkTo(listHeader.bottom)
                }, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(dummy) {
                        TransactionItem()
                    }
                }
            }
        }
}


@Composable
fun MainCardView(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = Zinc),
        modifier = modifier
            .padding(24.dp) // outer padding - margin
            .height(200.dp) // fixed height
    ) {
        Box(modifier = Modifier.padding(24.dp).fillMaxSize()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(Alignment.TopCenter)) {
                Column (modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Total Balance", color = Color.White)
                    Text("Rs. 2993.09", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.White)
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null, tint = Color.White)
                }
            }

            Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)){
                IncomeExpenseText("Income", "$ 1,884.00")
                IncomeExpenseText("Expense", "$ 3,384.00")
            }

        }
    }

    // if by row and column, then
    // column -> row { Column + icon } -> row { Column { Text with drawable + Text }}
}

@Composable
fun TransactionItem() {
    Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 12.dp) ) {
        Image(imageVector = Icons.Default.ThumbUp, contentDescription = null, modifier = Modifier.size(36.dp))
        Spacer(Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("Upwork", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text("Date")
        }
        Text("+$98000", style = MaterialTheme.typography.titleMedium)
    }
}


@Composable
fun IncomeExpenseText(label: String, amount: String) {
    Column {
        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null, tint = Color.White)
            Spacer(Modifier.width(4.dp))
            Text(label, color = Color.White)
        }
        Spacer(Modifier.height(4.dp))
        Text(amount, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

val dummy = listOf(
    1,2,3,4,5
)