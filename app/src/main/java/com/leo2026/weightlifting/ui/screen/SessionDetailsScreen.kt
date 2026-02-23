package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leo2026.weightlifting.data.entity.SetEntryEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionDetailsScreen(
    sessionId: String,
    viewModel: WorkoutViewModel,
    onNavigateBack: () -> Unit
) {
    val sessions by viewModel.completedSessions.collectAsState()
    val sessionSets by viewModel.getSetsForSession(sessionId).collectAsState(initial = emptyList())
    
    val session = sessions.find { it.id == sessionId }
    
    val groupedSets = remember(sessionSets) {
        sessionSets.groupBy { it.exerciseId }
    }

    val totalNetMillis = remember(sessionSets) {
        sessionSets.sumOf { it.durationMillis }
    }

    // --- FUNCIÓN DE TRADUCCIÓN DE TIEMPO HUMANO ---
    fun formatTime(millis: Long): String {
        val totalSecs = millis / 1000
        val hrs = totalSecs / 3600
        val mins = (totalSecs % 3600) / 60
        val secs = totalSecs % 60

        return when {
            hrs > 0 -> "${hrs}hrs, ${mins}min"
            mins > 0 -> "${mins}min, ${secs}segs"
            else -> "${secs}segs"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(session?.name ?: "Detalles") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                session?.let {
                    val date = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(it.startTime))
                    Text("Fecha: $date", style = MaterialTheme.typography.bodyLarge)
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Timer, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Esfuerzo neto total: ${formatTime(totalNetMillis)}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Ejercicios realizados:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            groupedSets.forEach { (_, sets) ->
                val firstSet = sets.firstOrNull()
                val exerciseName = firstSet?.exerciseNameSnapshot ?: "Ejercicio"
                val exerciseCategory = firstSet?.exerciseCategorySnapshot ?: ""
                
                val exerciseNetMillis = sets.sumOf { it.durationMillis }

                item {
                    Column(modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = exerciseName.uppercase(),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp,
                                modifier = Modifier.weight(1f)
                            )
                            // SUB-TOTAL POR EJERCICIO (Notación Mejorada)
                            Text(
                                text = formatTime(exerciseNetMillis),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                        if (exerciseCategory.isNotBlank()) {
                            Text(
                                text = "($exerciseCategory)",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                itemsIndexed(sets) { index, set ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Serie ${index + 1}:  ${set.weight} kg x ${set.reps}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        
                        Text(
                            text = "Descanso: ${set.exerciseRestSnapshot}s",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
                
                item {
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp), 
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}
