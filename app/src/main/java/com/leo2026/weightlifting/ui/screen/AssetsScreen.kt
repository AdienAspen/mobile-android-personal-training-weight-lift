package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
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
        containerColor = Color(0xFF121212), // Fondo global negro carbón
        topBar = {
            // UNIFICACIÓN DEL HEAD (FRANJA TOP)
            TopAppBar(
                title = {
                    Text(
                        "Mis Implementos",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Normal // CORRECCIÓN: Quitamos el "Black" para que sea delgado y limpio
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E), // Gris oscuro unificado
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // SECCIÓN BARRAS
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("BARRAS / SOPORTES", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFFFF6D00))
                    IconButton(onClick = { showAddBar = true }) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color(0xFFFF6D00))
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.Gray.copy(alpha = 0.2f))
            }

            if (bars.isEmpty()) {
                item { Text("No hay barras registradas.", color = Color.Gray, style = MaterialTheme.typography.bodySmall) }
            } else {
                items(bars) { bar ->
                    BarItem(bar, onDelete = { viewModel.deleteBar(bar) })
                }
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            // SECCIÓN DISCOS
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("DISCOS / PESOS", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFFFF6D00))
                    IconButton(onClick = { showAddPlate = true }) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color(0xFFFF6D00))
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.Gray.copy(alpha = 0.2f))
            }

            if (plates.isEmpty()) {
                item { Text("No hay discos registrados.", color = Color.Gray, style = MaterialTheme.typography.bodySmall) }
            } else {
                items(plates) { plate ->
                    PlateItem(plate, onDelete = { viewModel.deletePlate(plate) })
                }
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
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
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = Color(0xFFFF6D00))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = bar.name.uppercase(), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "${bar.weight} kg", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color(0xFFD32F2F))
            }
        }
    }
}

@Composable
fun PlateItem(plate: PlateEntity, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Layers, contentDescription = null, tint = Color(0xFFFF6D00).copy(alpha = 0.7f))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "DISCO: ${plate.weight} KG", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "Cantidad: ${plate.quantity}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color(0xFFD32F2F))
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
        containerColor = Color(0xFF1E1E1E),
        titleContentColor = Color(0xFFFF6D00),
        textContentColor = Color.White,
        title = { Text("NUEVA BARRA / SOPORTE") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre (ej: Barra Recta)") },
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = weight, 
                    onValueChange = { weight = it }, 
                    label = { Text("Peso base (kg)") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, weight.toDoubleOrNull() ?: 0.0) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)) { Text("Añadir") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar", color = Color(0xFFFF6D00)) } }
    )
}

@Composable
fun AddPlateDialog(onDismiss: () -> Unit, onConfirm: (Double, Int) -> Unit) {
    var weight by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF1E1E1E),
        titleContentColor = Color(0xFFFF6D00),
        textContentColor = Color.White,
        title = { Text("NUEVO DISCO / CARGA") },
        text = {
            Column {
                TextField(
                    value = weight, 
                    onValueChange = { weight = it }, 
                    label = { Text("Peso del disco (kg)") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = qty, 
                    onValueChange = { qty = it }, 
                    label = { Text("Cantidad disponible") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
                    colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(weight.toDoubleOrNull() ?: 0.0, qty.toIntOrNull() ?: 0) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)) { Text("Añadir") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar", color = Color(0xFFFF6D00)) } }
    )
}
