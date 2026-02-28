package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: WorkoutViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val OrangePrimary = Color(0xFFFF6D00)
    val CharcoalBackground = Color(0xFF121212)
    val DarkGreySurface = Color(0xFF1E1E1E)

    Scaffold(
        containerColor = CharcoalBackground,
        topBar = {
            // MENÚ GLOBAL SUPER-TOP
            if (currentDestination != null && !currentDestination.startsWith("details")) {
                TopAppBar(
                    title = { },
                    actions = {
                        IconButton(onClick = { navController.navigate("analytics") }) {
                            Icon(Icons.Default.BarChart, contentDescription = "Analítica", tint = Color.White.copy(alpha = 0.6f))
                        }
                        IconButton(onClick = {
                            navController.navigate("home") {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        }) {
                            Icon(Icons.Default.Home, contentDescription = "Home", tint = OrangePrimary)
                        }
                        IconButton(onClick = { navController.navigate("settings") }) {
                            Icon(Icons.Default.Settings, contentDescription = "Ajustes", tint = Color.White.copy(alpha = 0.6f))
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = DarkGreySurface,
                tonalElevation = 0.dp
            ) {
                val items = listOf(
                    Triple("workout", "Entrenar", Icons.Default.FitnessCenter),
                    Triple("templates", "Rutinas", Icons.Default.ListAlt),
                    Triple("exercises", "Ejercicios", Icons.Default.LibraryBooks),
                    Triple("assets", "Equipo", Icons.Default.Inventory),
                    Triple("history", "Historial", Icons.Default.History)
                )

                items.forEach { (route, label, icon) ->
                    val isSelected = currentDestination == route
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) },
                        selected = isSelected,
                        onClick = {
                            // --- NAVEGACIÓN IMPERATIVA Y MANDATORIA ---

                            // Si el usuario pulsa "Entrenar", forzamos el reset del launcher
                            if (route == "workout") {
                                viewModel.resetToWorkoutLauncher()
                            }

                            navController.navigate(route) {
                                // Al navegar vía Bottom Menu, limpiamos el historial de esa pestaña
                                // para volver siempre a la "Página Madre" (Parent).
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = false // NO guardamos estado para evitar que se quede "pegada"
                                }
                                launchSingleTop = true
                                restoreState = false // NAVEGACIÓN LIMPIA: No restauramos niveles profundos
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = OrangePrimary,
                            selectedTextColor = OrangePrimary,
                            unselectedIconColor = Color.White.copy(alpha = 0.6f),
                            unselectedTextColor = Color.White.copy(alpha = 0.6f),
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { padding ->
        Surface(
            modifier = Modifier.padding(padding),
            color = CharcoalBackground
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        viewModel = viewModel,
                        onStartWorkout = {
                            viewModel.resetToWorkoutLauncher()
                            navController.navigate("workout")
                        },
                        onNavigateToHistory = { navController.navigate("history") }
                    )
                }
                composable("workout") {
                    WorkoutScreen(viewModel) { navController.navigate("history") }
                }
                composable("templates") {
                    TemplatesScreen(viewModel) {
                        navController.navigate("workout")
                    }
                }
                composable("exercises") {
                    ExercisesScreen(viewModel) {
                        navController.navigate("workout")
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
                composable("analytics") { PlaceholderScreen("Analítica", OrangePrimary) }
                composable("settings") { PlaceholderScreen("Ajustes", OrangePrimary) }
                
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
}

@Composable
fun PlaceholderScreen(title: String, color: Color) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(title, style = MaterialTheme.typography.headlineLarge, color = color, fontWeight = FontWeight.Black)
    }
}
