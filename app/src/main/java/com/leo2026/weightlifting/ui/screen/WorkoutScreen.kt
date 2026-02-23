package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.os.Build
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.leo2026.weightlifting.service.RestTimerService
import com.leo2026.weightlifting.data.entity.ExerciseEntity
import com.leo2026.weightlifting.data.entity.BarEntity
import com.leo2026.weightlifting.data.entity.PlateEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    viewModel: WorkoutViewModel,
    onNavigateToHistory: () -> Unit
) {
    val context = LocalContext.current
    val sessionId by viewModel.currentSessionId.collectAsState()
    val sessionName by viewModel.currentSessionName.collectAsState()
    val exercises by viewModel.exercises.collectAsState()
    val templates by viewModel.templates.collectAsState()
    val sets by viewModel.currentSets.collectAsState()
    val bars by viewModel.allBars.collectAsState()
    val plates by viewModel.allPlates.collectAsState()
    val timerTrigger by viewModel.timerTrigger.collectAsState()
    val prEvent by viewModel.newPREvents.collectAsState()

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var showAddExercise by remember { mutableStateOf(false) }
    var showSaveTemplate by remember { mutableStateOf(false) }
    var showTemplatePicker by remember { mutableStateOf(false) }
    
    var calculatorTarget by remember { mutableStateOf<Pair<Double, (Double) -> Unit>?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    var activeExerciseId by remember { mutableStateOf<String?>(null) }

    val timerIsRunning by RestTimerService.isRunning.collectAsState()
    val timeLeft by RestTimerService.timeLeft.collectAsState()

    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri ->
        uri?.let {
            scope.launch {
                val json = viewModel.exportData()
                context.contentResolver.openOutputStream(it)?.use { stream ->
                    stream.write(json.toByteArray())
                }
                snackbarHostState.showSnackbar("Copia de seguridad exportada")
            }
        }
    }

    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            context.contentResolver.openInputStream(it)?.use { stream ->
                val json = stream.bufferedReader().readText()
                viewModel.importData(json) {
                    scope.launch { snackbarHostState.showSnackbar("Datos restaurados") }
                }
            }
        }
    }

    LaunchedEffect(prEvent) {
        prEvent?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearPREvent()
        }
    }

    LaunchedEffect(timerTrigger) {
        timerTrigger?.let { seconds ->
            val intent = Intent(context, RestTimerService::class.java).apply {
                putExtra("SECONDS", seconds.toLong())
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
            viewModel.onTimerStarted()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            LargeTopAppBar(
                title = { 
                    Text(
                        text = if (sessionId != null) sessionName else "LEO-2026", 
                        fontWeight = FontWeight.Black
                    ) 
                },
                actions = {
                    if (sessionId != null) {
                        IconButton(onClick = { showAddExercise = true }) {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                        TextButton(onClick = { showSaveTemplate = true }) {
                            Text("GUARDAR")
                        }
                        IconButton(onClick = { viewModel.finishWorkout() }) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color.Red)
                        }
                    } else {
                         IconButton(onClick = onNavigateToHistory) {
                            Icon(Icons.Default.History, contentDescription = null)
                        }
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = null)
                        }
                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(
                                text = { Text("Exportar Datos") },
                                onClick = {
                                    showMenu = false
                                    exportLauncher.launch("leo_backup_${System.currentTimeMillis()}.json")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Importar Datos") },
                                onClick = {
                                    showMenu = false
                                    importLauncher.launch(arrayOf("application/json"))
                                }
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (timerIsRunning) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    tonalElevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("DESCANSO ACTIVO", style = MaterialTheme.typography.labelSmall)
                            Text("${timeLeft}s", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Black)
                        }
                        Button(onClick = { context.stopService(Intent(context, RestTimerService::class.java)) }) {
                            Text("SALTAR")
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (sessionId == null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                        onClick = { viewModel.startWorkout() }
                    ) {
                        Text("NUEVO ENTRENAMIENTO")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                        onClick = { viewModel.startFromLastSession() }
                    ) {
                        Text("REPETIR ÚLTIMO")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                        onClick = { showTemplatePicker = true }
                    ) {
                        Text("USAR PLANTILLA")
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    item {
                        Surface(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Rutina activa: ",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = sessionName,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    items(exercises) { exercise ->
                        ExerciseCard(
                            exercise = exercise,
                            viewModel = viewModel,
                            exerciseSets = sets.filter { it.exerciseId == exercise.id },
                            isResting = timerIsRunning,
                            isActive = activeExerciseId == exercise.id,
                            onActivate = { activeExerciseId = exercise.id },
                            onDeactivate = { if (activeExerciseId == exercise.id) activeExerciseId = null },
                            onShowError = { message -> 
                                scope.launch { snackbarHostState.showSnackbar(message) }
                            },
                            onCalculatePlates = { weight, onResult ->
                                calculatorTarget = weight to onResult 
                            }
                        )
                    }
                    item { Spacer(modifier = Modifier.height(120.dp)) }
                }
            }
        }
    }

    if (showTemplatePicker) {
        TemplatePicker(
            templates = templates,
            onDismiss = { showTemplatePicker = false },
            onSelect = { template ->
                viewModel.startWorkout(template.name, template.id)
                showTemplatePicker = false
            }
        )
    }

    if (showSaveTemplate) {
        SaveTemplateDialog(
            onDismiss = { showSaveTemplate = false },
            onConfirm = { name ->
                if (viewModel.saveCurrentAsTemplate(name)) showSaveTemplate = false
            }
        )
    }

    if (showAddExercise) {
        AddExerciseDialog(
            onDismiss = { showAddExercise = false },
            onConfirm = { name, category, rest ->
                if (viewModel.addExercise(name, category, rest)) showAddExercise = false
            }
        )
    }

    calculatorTarget?.let { (targetWeight, onWeightSelected) ->
        PlateCalculatorDialog(
            targetWeight = targetWeight,
            availableBars = bars,
            availablePlates = plates,
            onConfirm = { finalWeight ->
                onWeightSelected(finalWeight)
                calculatorTarget = null
            },
            onDismiss = { calculatorTarget = null }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlateCalculatorDialog(
    targetWeight: Double,
    availableBars: List<BarEntity>,
    availablePlates: List<PlateEntity>,
    onConfirm: (Double) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedBar by remember { mutableStateOf(availableBars.firstOrNull()) }
    var currentTarget by remember(targetWeight) { mutableStateOf(targetWeight) }
    var expandedBarMenu by remember { mutableStateOf(false) }
    
    val reachableWeights = remember(selectedBar, availablePlates) {
        val barWeight = selectedBar?.weight ?: 0.0
        var possiblePerSide = setOf(0.0)
        for (plate in availablePlates) {
            val nextPossible = mutableSetOf<Double>()
            val maxPairs = plate.quantity / 2
            for (p in possiblePerSide) {
                for (i in 0..maxPairs) {
                    nextPossible.add(p + (i * plate.weight))
                }
            }
            possiblePerSide = nextPossible
        }
        possiblePerSide.map { barWeight + (it * 2) }.sorted()
    }

    val isExactPossible = reachableWeights.any { Math.abs(it - currentTarget) < 0.01 }
    val lowerSuggestion = reachableWeights.filter { it < currentTarget }.lastOrNull()
    val upperSuggestion = reachableWeights.filter { it > currentTarget }.firstOrNull()

    val platesNeeded = remember(selectedBar, availablePlates, currentTarget) {
        val barWeight = selectedBar?.weight ?: 0.0
        val remainingPerSide = (currentTarget - barWeight) / 2.0
        val result = mutableListOf<Double>()
        if (remainingPerSide > 0) {
            var currentRemaining = remainingPerSide
            val sortedPlates = availablePlates.sortedByDescending { it.weight }
            for (plate in sortedPlates) {
                val maxPairs = plate.quantity / 2
                var pairsUsed = 0
                while (currentRemaining >= plate.weight - 0.001 && pairsUsed < maxPairs) {
                    result.add(plate.weight)
                    currentRemaining -= plate.weight
                    pairsUsed++
                }
            }
        }
        result
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Calculadora Inteligente") },
        text = {
            Column {
                if (availableBars.isEmpty()) {
                    Text("⚠️ Registra tu equipo", color = Color.Red)
                } else {
                    Text("SOPORTE:", style = MaterialTheme.typography.labelSmall)
                    OutlinedCard(
                        onClick = { expandedBarMenu = true },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    ) {
                        Row(modifier = Modifier.padding(12.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = selectedBar?.let { "${it.name} (${it.weight}kg)" } ?: "Seleccionar...")
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                    DropdownMenu(expanded = expandedBarMenu, onDismissRequest = { expandedBarMenu = false }) {
                        availableBars.forEach { bar ->
                            DropdownMenuItem(text = { Text("${bar.name} (${bar.weight}kg)") }, onClick = { selectedBar = bar; expandedBarMenu = false })
                        }
                    }

                    if (!isExactPossible) {
                        Text("No es posible armar ${targetWeight}kg.", color = Color.Red, style = MaterialTheme.typography.bodySmall)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            lowerSuggestion?.let { SuggestionChip(onClick = { currentTarget = it }, label = { Text("${it}kg") }) }
                            upperSuggestion?.let { SuggestionChip(onClick = { currentTarget = it }, label = { Text("${it}kg") }) }
                        }
                    }

                    Divider(modifier = Modifier.padding(vertical = 12.dp))
                    Text("DESGLOSE PARA ${currentTarget}kg:", fontWeight = FontWeight.Bold)
                    if (platesNeeded.isEmpty()) {
                        Text("SOLO EL SOPORTE", fontWeight = FontWeight.Black)
                    } else {
                        platesNeeded.groupBy { it }.forEach { (weight, list) ->
                            Text("${list.size} x ${weight}kg", fontWeight = FontWeight.Black, fontSize = 18.sp)
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(currentTarget) }) { Text("OK") }
        }
    )
}

@Composable
fun ExerciseCard(
    exercise: ExerciseEntity,
    viewModel: WorkoutViewModel,
    exerciseSets: List<com.leo2026.weightlifting.data.entity.SetEntryEntity>,
    isResting: Boolean,
    isActive: Boolean,
    onActivate: () -> Unit,
    onDeactivate: () -> Unit,
    onShowError: (String) -> Unit, // Nueva función para avisar errores
    onCalculatePlates: (Double, (Double) -> Unit) -> Unit
) {
    var weight by remember { mutableStateOf("") }
    var reps by remember { mutableStateOf("") }
    var rest by remember { mutableStateOf(exercise.defaultRestSeconds.toString()) }

    var isRunningSet by remember { mutableStateOf(false) }
    var startTime by remember { mutableLongStateOf(0L) }
    var elapsedTime by remember { mutableLongStateOf(0L) }

    LaunchedEffect(isRunningSet) {
        if (isRunningSet) {
            startTime = System.currentTimeMillis()
            while (isRunningSet) {
                elapsedTime = System.currentTimeMillis() - startTime
                delay(100)
            }
        } else {
            elapsedTime = 0L
        }
    }

    LaunchedEffect(isResting) {
        if (!isResting && !isRunningSet && isActive && exerciseSets.isNotEmpty()) {
            isRunningSet = true
        }
    }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = exercise.name.uppercase(), 
                style = MaterialTheme.typography.titleLarge, 
                fontWeight = FontWeight.Black,
                color = if (isActive) MaterialTheme.colorScheme.primary else Color.Unspecified
            )

            Spacer(modifier = Modifier.height(12.dp))
            
            if (isRunningSet) {
                val mins = (elapsedTime / 1000) / 60
                val secs = (elapsedTime / 1000) % 60
                val decs = (elapsedTime % 1000) / 100
                Text(
                    text = String.format("%02d:%02d:%d", mins, secs, decs),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (!isRunningSet) {
                Button(
                    onClick = {
                        // --- BLINDAJE: Validación de campos antes de START ---
                        val w = weight.toDoubleOrNull() ?: 0.0
                        val r = reps.toIntOrNull() ?: 0
                        val rSec = rest.toIntOrNull() ?: 0
                        
                        if (w <= 0 || r <= 0 || rSec <= 0) {
                            onShowError("⚠️ Completa: Peso, Reps y Descanso para iniciar.")
                        } else {
                            onActivate()
                            isRunningSet = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(60.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("START SERIE", fontWeight = FontWeight.Bold)
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {
                            val w = weight.toDoubleOrNull() ?: 0.0
                            val r = reps.toIntOrNull() ?: 0
                            val rSec = rest.toIntOrNull() ?: 90
                            if (w > 0 && r > 0) {
                                viewModel.addSet(exercise.id, w, r, rSec, elapsedTime)
                                isRunningSet = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Icon(Icons.Default.Stop, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("FINISHED", fontWeight = FontWeight.Bold)
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = {
                            isRunningSet = false
                            onDeactivate()
                        },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.Gray)
                    ) {
                        Icon(Icons.Default.ExitToApp, contentDescription = null, tint = Color.Gray)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("FINALIZAR EJERCICIO", color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Peso", fontSize = 10.sp) },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
                )
                IconButton(onClick = { 
                    val w = weight.toDoubleOrNull() ?: 0.0
                    if (w > 0) onCalculatePlates(w) { weight = it.toString() }
                }) {
                    Icon(Icons.Default.Calculate, contentDescription = null)
                }
                OutlinedTextField(
                    value = reps,
                    onValueChange = { reps = it },
                    label = { Text("Reps", fontSize = 10.sp) },
                    modifier = Modifier.weight(0.8f),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
                )
                Spacer(modifier = Modifier.width(4.dp))
                OutlinedTextField(
                    value = rest,
                    onValueChange = { rest = it },
                    label = { Text("Rest", fontSize = 10.sp) },
                    modifier = Modifier.weight(0.8f),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
                )
            }

            Divider(modifier = Modifier.padding(vertical = 12.dp))

            Column {
                exerciseSets.forEachIndexed { index, set ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Serie ${index + 1}: ${set.weight}kg x ${set.reps}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                        Text("${set.durationMillis / 1000}s", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}

@Composable
fun AddExerciseDialog(onDismiss: () -> Unit, onConfirm: (String, String, Int) -> Unit) {
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var rest by remember { mutableStateOf("90") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("NUEVO EJERCICIO") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = category, onValueChange = { category = it }, label = { Text("Categoría") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = rest, onValueChange = { rest = it }, label = { Text("Descanso (s)") })
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, category, rest.toIntOrNull() ?: 90) }) { Text("AÑADIR") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("CANCELAR") }
        }
    )
}

@Composable
fun SaveTemplateDialog(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("GUARDAR PLANTILLA") },
        text = {
            TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre de la rutina") })
        },
        confirmButton = {
            Button(onClick = { onConfirm(name) }) { Text("GUARDAR") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("CANCELAR") }
        }
    )
}

@Composable
fun TemplatePicker(templates: List<com.leo2026.weightlifting.data.entity.TemplateEntity>, onDismiss: () -> Unit, onSelect: (com.leo2026.weightlifting.data.entity.TemplateEntity) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("SELECCIONAR PLANTILLA") },
        text = {
            LazyColumn {
                items(templates) { template ->
                    TextButton(
                        onClick = { onSelect(template) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(template.name)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("CANCELAR") }
        }
    )
}
