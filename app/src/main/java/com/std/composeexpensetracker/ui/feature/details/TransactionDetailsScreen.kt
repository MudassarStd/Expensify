package com.std.composeexpensetracker.ui.feature.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.std.composeexpensetracker.R
import com.std.composeexpensetracker.ui.components.TopRowHeader
import com.std.composeexpensetracker.ui.theme.Zinc


// planing structure

// constraint layout
// bg
// top row header
// DataFormColumn


@Composable
fun TransactionDetailsScreen(modifier: Modifier = Modifier, navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // create refs
        val (bg, topHeaderRow, mainCol) = createRefs()

        Image(
            painter = painterResource(R.drawable.top_bg),
            contentDescription = null,
            modifier = Modifier.constrainAs(bg) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        TopRowHeader(modifier = Modifier.constrainAs(topHeaderRow){
            top.linkTo(parent.top)
        }, title = "Transaction Details", navController = navController)

        MainColumnDataForm(modifier = Modifier
            .constrainAs(mainCol) {
                top.linkTo(topHeaderRow.bottom)
            }
            .padding(top = 90.dp))
    }
}

@Composable
fun MainColumnDataForm(modifier: Modifier) {
    Column (modifier = modifier
        .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
        .background(color = Color.White).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Spacer(Modifier.height(24.dp))

        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = null)

        Spacer(Modifier.height(12.dp))

        ElevatedButton(onClick = {}) {
            Text("Income", color = Zinc)
        }
        Spacer(Modifier.height(4.dp))
        Text("$8649.00", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),) {
            Text("Transaction Details", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
            }
        }

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),) {
            Text("Status")
            Text("Income", color = Zinc)
        }
        
        DataFormItem("From", value = "Upwork Escrow")
        DataFormItem("Time", value = "10:00 AM")
        DataFormItem("Date", value = "Feb 22, 2025")
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp))
        DataFormItem("Earnings", value = "$850.00")
        DataFormItem("Fee", value = "$850.00")
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp))
        DataFormItem("Total", value = "$850.00")

        OutlinedButton(onClick = {}, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)) {
            Text("Download Receipt", color = Zinc)
        }

    }
}


@Composable
fun DataFormItem(label: String, value: String) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text(label)
        Text(value, fontWeight = FontWeight.Bold)
    }
}


@Preview(showBackground = true)
@Composable
private fun TransactionDetailsScreenPreview() {
//    TransactionDetailsScreen()
}