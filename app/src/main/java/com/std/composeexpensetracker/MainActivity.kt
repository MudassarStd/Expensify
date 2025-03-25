package com.std.composeexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.std.composeexpensetracker.ui.nav.AppNavigation
import com.std.composeexpensetracker.ui.theme.ComposeExpenseTrackerTheme
import com.std.composeexpensetracker.ui.theme.Zinc

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = Zinc.toArgb() // temporarily
        setContent {
            ComposeExpenseTrackerTheme {
                AppNavigation()


                // use scaffold later for bottom navigation

//                Scaffold(modifier = Modifier.fillMaxSize(),
//                    bottomBar = {
//                        NavigationBar {
//                            NavigationBarItem(
//                                selected = false,
//                                onClick = {},
//                                icon = {
//                                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
//                                }
//                            )
//
//                            NavigationBarItem(
//                                selected = false,
//                                onClick = {},
//                                icon = {
//                                    Icon(imageVector = Icons.Default.Star, contentDescription = null)
//                                }
//                            )
//                        }
//                    }) { innerPadding ->
//                    HomeScreen(Modifier.padding(innerPadding))
//                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun Greeting() {
//    ComposeExpenseTrackerTheme {
//        Scaffold(modifier = Modifier.fillMaxSize(),
//            bottomBar = {
//                NavigationBar (modifier = Modifier.fillMaxWidth()) {
//                    NavigationBarItem(
//                        selected = false,
//                        onClick = {},
//                        icon = {
//                            Icon(imageVector = Icons.Default.Home, contentDescription = null)
//                        }
//                    )
//
//                    NavigationBarItem(
//                        selected = false,
//                        onClick = {},
//                        icon = {
//                            Icon(imageVector = Icons.Default.Star, contentDescription = null)
//                        }
//                    )
//                }
//            },
//            floatingActionButton = {
//                FloatingActionButton(onClick = {},
//                    containerColor = Zinc) {
//                    Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
//                }
//            }) { innerPadding ->
//            HomeScreen(Modifier.padding(innerPadding))
//        }
//    }
//}

