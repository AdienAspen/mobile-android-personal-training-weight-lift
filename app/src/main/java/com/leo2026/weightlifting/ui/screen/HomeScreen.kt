package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leo2026.weightlifting.data.entity.UserEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    viewModel: WorkoutViewModel,
    onStartWorkout: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val user by viewModel.userProfile.collectAsState()
    val completedSessions by viewModel.completedSessions.collectAsState()
    
    // --- REPARACIÓN DE PERSISTENCIA ---
    // Usamos el valor del user directamente para controlar el diálogo, evitando el rebote del LaunchedEffect
    val isUserRegistered = user != null

    // --- ALGORITMO DE ANTIGÜEDAD (Time Ago) ---
    fun getTimeAgo(time: Long): String {
        val diff = System.currentTimeMillis() - time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            days > 0 -> "hace $days d"
            hours > 0 -> "hace $hours h"
            minutes > 0 -> "hace $minutes min"
            else -> "justo ahora"
        }
    }

    val motivationalPhrases = remember {
        listOf(
            "La consistencia es la clave para desatar todo tu potencial. Una repetición a la vez.",
            "El único entrenamiento malo es el que no se hizo.",
            "Tu cuerpo puede soportar casi cualquier cosa. Es a tu mente a la que tienes que convencer.",
            "La fuerza no viene de ganar. Tus luchas desarrollan tus fortalezas.",
            "La acción es la clave fundamental de todo éxito.",
            "No te detengas cuando estés cansado. Detente cuando hayas terminado.",
            "Lo difícil no es poner tu cuerpo en forma. Lo difícil es poner tu mente en forma.",
            "Un campeón es alguien que se levanta cuando no puede.",
            "Lo que duele hoy te hace más fuerte mañana.",
            "El éxito comienza con la autodisciplina.",
            "Si no te desafía, no te cambiará.",
            "Cree en ti mismo y en todo lo que eres.",
            "Tu única competencia es el espejo."
        )
    }

    val randomPhrase = remember { motivationalPhrases.random() }

    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E1E1E))
                    .padding(16.dp)
            ) {
                Text(text = "RENDIMIENTO", style = MaterialTheme.typography.labelSmall, color = Color(0xFFFF6D00))
                Text(
                    text = user?.let { "${it.firstName} ${it.lastName}" } ?: "CARGANDO...",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Alcanza tus\nmetas hoy.",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
                )
            }

            item {
                Button(
                    onClick = onStartWorkout,
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("INICIAR ENTRENAMIENTO", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black, color = Color.Black)
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("HISTORIAL RECIENTE", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
                    TextButton(onClick = onNavigateToHistory) {
                        Text("VER TODO", color = Color(0xFFFF6D00))
                    }
                }
            }

            if (completedSessions.isEmpty()) {
                item { Text("Aún no hay sesiones registradas.", color = Color.Gray, modifier = Modifier.padding(vertical = 16.dp)) }
            } else {
                items(completedSessions.take(2)) { session ->
                    val summaryState = viewModel.getSessionSummary(session.id).collectAsState(initial = null)
                    val summary = summaryState.value
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.size(48.dp).background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                val icon = if (summary?.names?.contains(",") == true) Icons.Default.Bolt else Icons.Default.FitnessCenter
                                Icon(icon, contentDescription = null, tint = Color(0xFFFF6D00), modifier = Modifier.size(24.dp))
                            }
                            
                            Spacer(modifier = Modifier.width(16.dp))
                            
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = summary?.names?.uppercase() ?: "CARGANDO...", 
                                    fontWeight = FontWeight.Black, 
                                    color = Color.White, 
                                    fontSize = 13.sp,
                                    lineHeight = 16.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(getTimeAgo(session.startTime), color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                                    Text(" • ", color = Color.Gray)
                                    Text(
                                        text = summary?.categories ?: "General", 
                                        color = Color(0xFFFF6D00), 
                                        style = MaterialTheme.typography.labelSmall,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }

                            Surface(
                                color = Color(0xFFFF6D00).copy(alpha = 0.1f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = if (summary?.names?.contains(",") == true) "+VOL" else "MANTENIDO", 
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFFFF6D00),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.sp
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E).copy(alpha = 0.5f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFF6D00).copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(text = "“", fontSize = 40.sp, fontWeight = FontWeight.Black, color = Color(0xFFFF6D00).copy(alpha = 0.5f), modifier = Modifier.height(30.dp))
                        Text(text = randomPhrase, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, color = Color.White, textAlign = TextAlign.Start)
                    }
                }
            }
            
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }

    // --- DIÁLOGO DE REGISTRO ESTABILIZADO ---
    if (!isUserRegistered) {
        var inputName by remember { mutableStateOf("") }
        var inputLastName by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { },
            containerColor = Color(0xFF1E1E1E),
            titleContentColor = Color(0xFFFF6D00),
            title = { Text("Bienvenido a LEO-2026") },
            text = {
                Column {
                    Text("Por favor identifícate para personalizar tu experiencia.", color = Color.White, modifier = Modifier.padding(bottom = 16.dp))
                    TextField(
                        value = inputName, 
                        onValueChange = { inputName = it }, 
                        label = { Text("Nombre") }, 
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = inputLastName, 
                        onValueChange = { inputLastName = it }, 
                        label = { Text("Apellido") }, 
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { 
                        if (inputName.isNotBlank() && inputLastName.isNotBlank()) { 
                            viewModel.updateProfile(inputName, inputLastName)
                        } 
                    }, 
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)
                ) {
                    Text("REGISTRAR", fontWeight = FontWeight.Black)
                }
            }
        )
    }
}
