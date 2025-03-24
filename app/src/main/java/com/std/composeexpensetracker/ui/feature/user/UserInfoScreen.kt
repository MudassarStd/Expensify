package com.std.composeexpensetracker.ui.feature.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.std.composeexpensetracker.ui.feature.MainViewModel
import com.std.composeexpensetracker.ui.theme.Zinc
import org.koin.androidx.compose.getViewModel

@Composable
fun UserInfoScreen(modifier: Modifier = Modifier) {

    val viewmodel: MainViewModel = getViewModel()

    var nameState by remember { mutableStateOf("") }
    var ageState by remember { mutableStateOf("") }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Zinc)
            .fillMaxSize()
    ) {

        Text(
            "Welcome to Expensify",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(24.dp))

        Card (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
            elevation = CardDefaults.cardElevation(12.dp)) {
            Column (verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(24.dp)) {
                Text("Name")
                OutlinedTextField(
                    value = nameState,
                    onValueChange = { nameState = it },
                    placeholder = {
                        Text("John")
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                )

                Text("Age")
                OutlinedTextField(
                    value = ageState,
                    onValueChange = { ageState = it},
                    placeholder = {
                        Text("20")
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                )

                Spacer(Modifier.height(8.dp))
                OutlinedButton(onClick = {
//                    viewmodel.saveUser(nameState, ageState)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Save", color = Zinc)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun P() {
    UserInfoScreen()
}
