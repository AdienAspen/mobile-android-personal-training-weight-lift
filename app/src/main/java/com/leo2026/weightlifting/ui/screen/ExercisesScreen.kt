package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import com.leo2026.weightlifting.data.entity.ExerciseEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen(
    viewModel: WorkoutViewModel,
    onStartWorkout: () -> Unit
) {
    val exercises by viewModel.allExercises.collectAsState()
    var exerciseToEdit by remember { mutableStateOf<ExerciseEntity?>(null) }
    var exerciseToDelete by remember { mutableStateOf<ExerciseEntity?>(null) }
    var showAddExercise by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Biblioteca de Ejercicios") },
                actions = {
                    IconButton(onClick = { showAddExercise = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Añadir Ejercicio")
                    }
                }
            )
        }
    ) { padding ->
        if (exercises.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No hay ejercicios en la biblioteca.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(exercises) { exercise ->
                    ExerciseItem(
                        exercise = exercise,
                        onEdit = { exerciseToEdit = exercise },
                        onDelete = { exerciseToDelete = exercise },
                        onClick = {
                            viewModel.startWorkoutWithExercise(exercise)
                            onStartWorkout()
                        }
                    )
                }
            }
        }
    }

    if (showAddExercise) {
        AddExerciseDialog(
            onDismiss = { showAddExercise = false },
            onConfirm = { name, category, rest ->
                if (viewModel.addExercise(name, category, rest)) {
                    showAddExercise = false
                }
            }
        )
    }

    exerciseToEdit?.let { selectedExercise ->
        var editName by remember { mutableStateOf(selectedExercise.name) }
        var editCategory by remember { mutableStateOf(selectedExercise.category) }
        var editRest by remember { mutableStateOf(selectedExercise.defaultRestSeconds.toString()) }
        
        AlertDialog(
            onDismissRequest = { exerciseToEdit = null },
            title = { Text("Editar Ejercicio") },
            text = {
                Column {
                    TextField(value = editName, onValueChange = { editName = it }, label = { Text("Nombre") })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(value = editCategory, onValueChange = { editCategory = it }, label = { Text("Categoría") })
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = editRest, 
                        onValueChange = { editRest = it }, 
                        label = { Text("Descanso (segundos)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    val updated = selectedExercise.copy(
                        name = editName,
                        category = editCategory,
                        defaultRestSeconds = editRest.toIntOrNull() ?: 90
                    )
                    if (viewModel.updateExercise(updated)) {
                        exerciseToEdit = null
                    }
                }) { Text("Guardar") }
            },
            dismissButton = {
                TextButton(onClick = { exerciseToEdit = null }) { Text("Cancelar") }
            }
        )
    }

    exerciseToDelete?.let { exercise ->
        AlertDialog(
            onDismissRequest = { exerciseToDelete = null },
            title = { Text("Eliminar Ejercicio") },
            text = { Text("¿Estás seguro de eliminar \"${exercise.name}\"? Esto no afectará a tus entrenamientos pasados (Soft Delete activado).") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteExercise(exercise)
                        exerciseToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { exerciseToDelete = null }) { Text("Cancelar") }
            }
        )
    }
}

@Composable
fun ExerciseItem(
    exercise: ExerciseEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = exercise.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "${exercise.category} • Descanso: ${exercise.defaultRestSeconds}s", 
                    style = MaterialTheme.typography.bodySmall, 
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Row {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
