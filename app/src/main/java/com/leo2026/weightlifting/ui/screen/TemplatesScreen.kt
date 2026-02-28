package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leo2026.weightlifting.data.entity.ExerciseEntity
import com.leo2026.weightlifting.data.entity.TemplateEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplatesScreen(
    viewModel: WorkoutViewModel,
    onStartWorkout: () -> Unit
) {
    val templates by viewModel.templates.collectAsState()
    val allExercises by viewModel.allExercises.collectAsState()

    var routineToEdit by remember { mutableStateOf<TemplateEntity?>(null) }
    var showEditDialog by remember { mutableStateOf(false) }
    var templateToDelete by remember { mutableStateOf<TemplateEntity?>(null) }

    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            TopAppBar(
                title = { Text("Mis Rutinas", style = MaterialTheme.typography.titleLarge) },
                actions = {
                    IconButton(onClick = {
                        routineToEdit = null
                        showEditDialog = true
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Nueva Rutina", tint = Color(0xFFFF6D00))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        if (templates.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No tienes rutinas guardadas.", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(templates) { template ->
                    TemplateItem(
                        template = template,
                        onEdit = {
                            routineToEdit = template
                            showEditDialog = true
                        },
                        onDelete = { templateToDelete = template },
                        onClick = {
                            viewModel.startWorkout(template.name, template.id)
                            onStartWorkout()
                        }
                    )
                }
            }
        }
    }

    if (showEditDialog) {
        RoutineEditorDialog(
            template = routineToEdit,
            allExercises = allExercises,
            viewModel = viewModel,
            onDismiss = { showEditDialog = false },
            onSave = { name, exerciseIds ->
                viewModel.saveRoutine(name, exerciseIds, routineToEdit?.id)
                showEditDialog = false
            }
        )
    }

    templateToDelete?.let { template ->
        AlertDialog(
            onDismissRequest = { templateToDelete = null },
            containerColor = Color(0xFF1E1E1E),
            titleContentColor = Color(0xFFFF6D00),
            textContentColor = Color.White,
            title = { Text("Eliminar Rutina") },
            text = { Text("¿Estás seguro de eliminar la rutina \"${template.name}\"?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteTemplate(template.id)
                        templateToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)
                ) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { templateToDelete = null }) { Text("Cancelar", color = Color(0xFFFF6D00)) }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineEditorDialog(
    template: TemplateEntity?,
    allExercises: List<ExerciseEntity>,
    viewModel: WorkoutViewModel,
    onDismiss: () -> Unit,
    onSave: (String, List<String>) -> Unit
) {
    var name by remember { mutableStateOf(template?.name ?: "") }
    val selectedIds = remember { mutableStateListOf<String>() }
    var showAddExerciseInside by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Cargar ejercicios actuales si estamos editando
    LaunchedEffect(template) {
        if (template != null) {
            viewModel.getExerciseIdsForTemplate(template.id).collect { ids ->
                selectedIds.clear()
                selectedIds.addAll(ids)
            }
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF1E1E1E),
        titleContentColor = Color(0xFFFF6D00),
        title = { Text(if (template == null) "NUEVA RUTINA" else "EDITAR RUTINA") },
        text = {
            Column(modifier = Modifier.fillMaxWidth().heightIn(max = 400.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre de la rutina") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFF6D00),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(0xFFFF6D00),
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("EJERCICIOS", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                    IconButton(onClick = { showAddExerciseInside = true }) {
                        Icon(Icons.Default.AddCircle, contentDescription = null, tint = Color(0xFFFF6D00))
                    }
                }

                Divider(color = Color.Gray.copy(alpha = 0.2f), modifier = Modifier.padding(vertical = 8.dp))

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(allExercises) { exercise ->
                        val isSelected = selectedIds.contains(exercise.id)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (isSelected) selectedIds.remove(exercise.id)
                                    else selectedIds.add(exercise.id)
                                }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isSelected,
                                onCheckedChange = {
                                    if (it) selectedIds.add(exercise.id)
                                    else selectedIds.remove(exercise.id)
                                },
                                colors = CheckboxDefaults.colors(checkedColor = Color(0xFFFF6D00))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(exercise.name, color = Color.White, fontSize = 14.sp)
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { if (name.isNotBlank()) onSave(name, selectedIds.toList()) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)
            ) { Text("GUARDAR", fontWeight = FontWeight.Black) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("CANCELAR", color = Color(0xFFFF6D00)) }
        }
    )

    if (showAddExerciseInside) {
        AddExerciseDialog(
            onDismiss = { showAddExerciseInside = false },
            onConfirm = { eName, category, rest ->
                scope.launch {
                    val newId = viewModel.addExerciseAndGetId(eName, category, rest)
                    if (newId != null) {
                        selectedIds.add(newId)
                        showAddExerciseInside = false
                    }
                }
            }
        )
    }
}

@Composable
fun TemplateItem(
    template: TemplateEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = template.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Row {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color(0xFFFF6D00))
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
        }
    }
}
