package com.leo2026.weightlifting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leo2026.weightlifting.data.database.AppDatabase
import com.leo2026.weightlifting.data.repository.WorkoutRepository
import com.leo2026.weightlifting.ui.screen.MainScreen
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel
import com.leo2026.weightlifting.ui.theme.LEO2026Typography // Importamos la nueva tipografía

// PALETA OFICIAL STITCH - AJUSTADA A TONOS CÁLIDOS (Adiós al azul)
private val OrangePrimary = Color(0xFFFF6D00)
private val CharcoalBackground = Color(0xFF121111) // Un tono más cálido que el gris puro
private val DarkGreySurface = Color(0xFF1E1C1B)    // Superficie con matiz tierra suave
private val PureWhite = Color(0xFFFFFFFF)

private val LEO2026DarkColorScheme = darkColorScheme(
    primary = OrangePrimary,
    onPrimary = Color.Black,
    secondary = OrangePrimary,
    onSecondary = Color.Black,
    surface = DarkGreySurface,
    onSurface = PureWhite,
    background = CharcoalBackground,
    onBackground = PureWhite,
    surfaceTint = Color.Transparent
)

@Composable
fun LEO2026Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LEO2026DarkColorScheme,
        typography = LEO2026Typography, // Aplicamos Rubik (vía Type.kt)
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AppDatabase.getDatabase(this)
        val repository = WorkoutRepository(
            database.exerciseDao(),
            database.workoutDao(),
            database.templateDao(),
            database.assetDao(),
            database.userDao()
        )
        val viewModelFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WorkoutViewModel(repository) as T
            }
        }

        setContent {
            LEO2026Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CharcoalBackground
                ) {
                    val viewModel: WorkoutViewModel = viewModel(factory = viewModelFactory)
                    MainScreen(viewModel)
                }
            }
        }
    }
}
