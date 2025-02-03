package com.std.composeexpensetracker.ui.feature.wallet

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.std.composeexpensetracker.R
import com.std.composeexpensetracker.ui.components.TopRowHeader
import com.std.composeexpensetracker.ui.feature.home.TransactionItem
import com.std.composeexpensetracker.ui.feature.home.dummy

// planning structure
// Box -> bg -> topRow -> mainColumn


@Composable
fun MainWalletScreen(
    modifier: Modifier = Modifier, ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Image(painter = painterResource(R.drawable.top_bg), contentDescription = null)

        TopRowHeader(title = "Wallet", modifier = Modifier , navController = null)

        MainColumn(Modifier.padding(top = 96.dp))
        
    }
}

@Composable
fun MainColumn(modifier: Modifier = Modifier) {
    Surface(modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topEndPercent = 10, topStartPercent = 10)),)
    {
        Column (
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally){

            Spacer(Modifier.height(24.dp))

            Text("Total Balance")
            Text("Rs 3492.43", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(Modifier.height(24.dp))

            // options row (add, pay and send)
            Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                Column {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Spacer(Modifier.height(2.dp))
                    Text("Add")
                }

                Column {
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
                    Spacer(Modifier.height(2.dp))
                    Text("Pay")
                }
                Column {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null)
                    Spacer(Modifier.height(2.dp))
                    Text("Send")
                }
            }
            Spacer(Modifier.height(32.dp))

            TabRow(selectedTabIndex = 1) {
                Tab(selected = false, onClick = {}) {
                    Text("Transactions")
                }

                Tab(selected = true, onClick = {}) {
                    Text("Upcoming Bills")
                }
            }

            Spacer(Modifier.height(24.dp))

            LazyColumn {
                items(dummy) {
                    TransactionItem()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PrevWallet() {
    MainWalletScreen()
}