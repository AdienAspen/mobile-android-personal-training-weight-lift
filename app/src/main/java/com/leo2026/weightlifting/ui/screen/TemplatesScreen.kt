package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.leo2026.weightlifting.data.entity.TemplateEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplatesScreen(
    viewModel: WorkoutViewModel,
    onStartWorkout: () -> Unit // Parámetro de navegación corregido
) {
    val templates by viewModel.templates.collectAsState()
    var templateToEdit by remember { mutableStateOf<TemplateEntity?>(null) }
    var templateToDelete by remember { mutableStateOf<TemplateEntity?>(null) }

    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            TopAppBar(
                title = { Text("Mis Rutinas") },
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
                        onEdit = { templateToEdit = template },
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

    templateToEdit?.let { template ->
        var newName by remember { mutableStateOf(template.name) }
        AlertDialog(
            onDismissRequest = { templateToEdit = null },
            containerColor = Color(0xFF1E1E1E),
            titleContentColor = Color(0xFFFF6D00),
            textContentColor = Color.White,
            title = { Text("Renombrar Rutina") },
            text = {
                TextField(value = newName, onValueChange = { newName = it }, label = { Text("Nuevo nombre") })
            },
            confirmButton = {
                Button(onClick = {
                    if (viewModel.updateTemplateName(template.id, newName)) {
                        templateToEdit = null
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)) { Text("Guardar") }
            },
            dismissButton = {
                TextButton(onClick = { templateToEdit = null }) { Text("Cancelar", color = Color(0xFFFF6D00)) }
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
            text = { Text("¿Estás seguro de que quieres eliminar la rutina \"${template.name}\"?") },
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
            Text(text = template.name, style = MaterialTheme.typography.titleMedium, color = Color.White)
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
