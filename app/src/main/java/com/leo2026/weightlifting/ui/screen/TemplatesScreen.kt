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
import androidx.compose.ui.unit.dp
import com.leo2026.weightlifting.data.entity.TemplateEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplatesScreen(
    viewModel: WorkoutViewModel,
    onStartWorkout: () -> Unit
) {
    val templates by viewModel.templates.collectAsState()
    var templateToEdit by remember { mutableStateOf<TemplateEntity?>(null) }
    var templateToDelete by remember { mutableStateOf<TemplateEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mis Rutinas") })
        }
    ) { padding ->
        if (templates.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No tienes rutinas guardadas.")
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
            title = { Text("Renombrar Rutina") },
            text = {
                TextField(value = newName, onValueChange = { newName = it }, label = { Text("Nuevo nombre") })
            },
            confirmButton = {
                Button(onClick = {
                    if (viewModel.updateTemplateName(template.id, newName)) {
                        templateToEdit = null
                    }
                }) { Text("Guardar") }
            },
            dismissButton = {
                TextButton(onClick = { templateToEdit = null }) { Text("Cancelar") }
            }
        )
    }

    templateToDelete?.let { template ->
        AlertDialog(
            onDismissRequest = { templateToDelete = null },
            title = { Text("Eliminar Rutina") },
            text = { Text("¿Estás seguro de que quieres eliminar la rutina \"${template.name}\"? Esto no afectará a tu historial pasado.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteTemplate(template.id)
                        templateToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { templateToDelete = null }) { Text("Cancelar") }
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
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = template.name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
            Row {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
