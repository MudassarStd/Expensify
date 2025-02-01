package com.std.composeexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.std.composeexpensetracker.ui.HomeScreen
import com.std.composeexpensetracker.ui.theme.ComposeExpenseTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExpenseTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            NavigationRailItem(
                                selected = false,
                                onClick = {},
                                icon = {
                                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                }
                            )

                            NavigationRailItem(
                                selected = false,
                                onClick = {},
                                icon = {
                                    Icon(imageVector = Icons.Default.Star, contentDescription = null)
                                }
                            )
                        }
                    }) { innerPadding ->
                    HomeScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Greeting() {
    ComposeExpenseTrackerTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    NavigationRailItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(imageVector = Icons.Default.Home, contentDescription = null)
                        }
                    )
                }
            }) { innerPadding ->
            HomeScreen(Modifier.padding(innerPadding))
        }
    }
}

