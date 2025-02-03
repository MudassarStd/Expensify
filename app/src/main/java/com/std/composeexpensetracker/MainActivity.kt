package com.std.composeexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.std.composeexpensetracker.ui.navgraph.AppNavigation
import com.std.composeexpensetracker.ui.theme.ComposeExpenseTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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

