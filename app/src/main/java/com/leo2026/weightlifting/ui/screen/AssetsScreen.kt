package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.leo2026.weightlifting.data.entity.BarEntity
import com.leo2026.weightlifting.data.entity.PlateEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetsScreen(viewModel: WorkoutViewModel) {
    val bars by viewModel.allBars.collectAsState()
    val plates by viewModel.allPlates.collectAsState()
    
    var showAddBar by remember { mutableStateOf(false) }
    var showAddPlate by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mis Implementos") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // SECCIÓN BARRAS
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Barras / Soportes", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    IconButton(onClick = { showAddBar = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Añadir Barra", tint = MaterialTheme.colorScheme.primary)
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }

            if (bars.isEmpty()) {
                item { Text("No hay barras registradas.", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(vertical = 8.dp)) }
            } else {
                items(bars) { bar ->
                    BarItem(bar, onDelete = { viewModel.deleteBar(bar) })
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // SECCIÓN DISCOS
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Discos / Pesos", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    IconButton(onClick = { showAddPlate = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Añadir Disco", tint = MaterialTheme.colorScheme.primary)
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }

            if (plates.isEmpty()) {
                item { Text("No hay discos registrados.", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(vertical = 8.dp)) }
            } else {
                items(plates) { plate ->
                    PlateItem(plate, onDelete = { viewModel.deletePlate(plate) })
                }
            }
        }
    }

    if (showAddBar) {
        AddBarDialog(
            onDismiss = { showAddBar = false },
            onConfirm = { name, weight ->
                viewModel.addBar(name, weight)
                showAddBar = false
            }
        )
    }

    if (showAddPlate) {
        AddPlateDialog(
            onDismiss = { showAddPlate = false },
            onConfirm = { weight, qty ->
                viewModel.addPlate(weight, qty)
                showAddPlate = false
            }
        )
    }
}

@Composable
fun BarItem(bar: BarEntity, onDelete: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = bar.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = "${bar.weight} kg", style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Borrar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun PlateItem(plate: PlateEntity, onDelete: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Layers, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Disco: ${plate.weight} kg", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Cantidad: ${plate.quantity}", style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Borrar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun AddBarDialog(onDismiss: () -> Unit, onConfirm: (String, Double) -> Unit) {
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva Barra / Soporte") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre (ej: Barra Recta)") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = weight, 
                    onValueChange = { weight = it }, 
                    label = { Text("Peso base (kg)") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, weight.toDoubleOrNull() ?: 0.0) }) { Text("Añadir") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

@Composable
fun AddPlateDialog(onDismiss: () -> Unit, onConfirm: (Double, Int) -> Unit) {
    var weight by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Disco / Carga") },
        text = {
            Column {
                TextField(
                    value = weight, 
                    onValueChange = { weight = it }, 
                    label = { Text("Peso del disco (kg)") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = qty, 
                    onValueChange = { qty = it }, 
                    label = { Text("Cantidad disponible") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(weight.toDoubleOrNull() ?: 0.0, qty.toIntOrNull() ?: 0) }) { Text("Añadir") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}
