package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel

@Composable
fun MainScreen(viewModel: WorkoutViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Entrenar") },
                    label = { Text("Entrenar") },
                    selected = currentDestination == "workout", 
                    onClick = { 
                        navController.navigate("workout") { 
                            popUpTo("workout") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        } 
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ListAlt, contentDescription = "Rutinas") },
                    label = { Text("Rutinas") },
                    selected = currentDestination == "templates",
                    onClick = { 
                        navController.navigate("templates") {
                            popUpTo("workout") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.LibraryBooks, contentDescription = "Ejercicios") },
                    label = { Text("Ejercicios") },
                    selected = currentDestination == "exercises",
                    onClick = { 
                        navController.navigate("exercises") {
                            popUpTo("workout") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Inventory, contentDescription = "Equipo") },
                    label = { Text("Equipo") },
                    selected = currentDestination == "assets",
                    onClick = {
                        navController.navigate("assets") {
                            popUpTo("workout") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.History, contentDescription = "Historial") },
                    label = { Text("Historial") },
                    selected = currentDestination == "history",
                    onClick = { 
                        navController.navigate("history") {
                            popUpTo("workout") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = "workout", modifier = Modifier.padding(padding)) {
            composable("workout") {
                WorkoutScreen(viewModel) { navController.navigate("history") }
            }
            composable("templates") {
                TemplatesScreen(viewModel) {
                    navController.navigate("workout") {
                        popUpTo("workout") { inclusive = true }
                    }
                }
            }
            composable("exercises") {
                ExercisesScreen(viewModel) {
                    navController.navigate("workout") {
                        popUpTo("workout") { inclusive = true }
                    }
                }
            }
            composable("assets") {
                AssetsScreen(viewModel)
            }
            composable("history") {
                HistoryScreen(
                    viewModel = viewModel,
                    onNavigateToDetails = { sessionId -> navController.navigate("details/$sessionId") },
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            composable(
                route = "details/{sessionId}",
                arguments = listOf(navArgument("sessionId") { type = NavType.StringType })
            ) { backStackEntry ->
                val sessionId = backStackEntry.arguments?.getString("sessionId") ?: ""
                SessionDetailsScreen(sessionId, viewModel) { navController.popBackStack() }
            }
        }
    }
}
