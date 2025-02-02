package com.std.composeexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.std.composeexpensetracker.ui.feature.HomeScreen
import com.std.composeexpensetracker.ui.theme.ComposeExpenseTrackerTheme
import com.std.composeexpensetracker.ui.theme.Zinc

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExpenseTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = false,
                                onClick = {},
                                icon = {
                                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                                }
                            )

                            NavigationBarItem(
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
                NavigationBar (modifier = Modifier.fillMaxWidth()) {
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(imageVector = Icons.Default.Home, contentDescription = null)
                        }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(imageVector = Icons.Default.Star, contentDescription = null)
                        }
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {},
                    containerColor = Zinc) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
                }
            }) { innerPadding ->
            HomeScreen(Modifier.padding(innerPadding))
        }
    }
}

