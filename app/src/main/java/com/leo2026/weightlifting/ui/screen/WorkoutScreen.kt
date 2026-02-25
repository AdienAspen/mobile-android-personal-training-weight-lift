package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    var activeExerciseId by remember { mutableStateOf<String?>(null) }

    val timerIsRunning by RestTimerService.isRunning.collectAsState()
    val timeLeft by RestTimerService.timeLeft.collectAsState()

    val OrangePrimary = Color(0xFFFF6D00)
    val CharcoalBackground = Color(0xFF121212)
    val DarkGreySurface = Color(0xFF1E1E1E)

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
        containerColor = CharcoalBackground
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().background(CharcoalBackground)) {
            if (sessionId == null) {
                // --- PANTALLA DE LANZAMIENTO (EL REGRESO) ---
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 1. Franja Delgada "Rubik"
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Entrenamiento Rubik",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(6.dp).background(OrangePrimary, CircleShape))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "EN VIVO",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = OrangePrimary,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                            }
                            Text(
                                text = "Ayuda",
                                style = MaterialTheme.typography.labelLarge,
                                color = OrangePrimary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable { /* Ayuda */ }
                            )
                        }
                    }

                    // 2. Hero Block: Tarjeta de Plan Activo
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                            colors = CardDefaults.cardColors(containerColor = DarkGreySurface),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        color = OrangePrimary,
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            "PLAN ACTIVO",
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        "S3/8",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = OrangePrimary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text("Full Body Pro", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Black, color = Color.White)
                                Text("Fuerza e Hipertrofia", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                                Spacer(modifier = Modifier.height(24.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                                    Column {
                                        Text("PROGRESO GENERAL", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                                        Row(verticalAlignment = Alignment.Bottom) {
                                            Text("35", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Black, color = Color.White)
                                            Text("%", style = MaterialTheme.typography.titleMedium, color = Color.Gray, modifier = Modifier.padding(bottom = 4.dp, start = 2.dp))
                                        }
                                    }
                                    Surface(color = Color.White.copy(alpha = 0.05f), shape = RoundedCornerShape(12.dp)) {
                                        Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.CalendarToday, contentDescription = null, tint = OrangePrimary, modifier = Modifier.size(14.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("SIGUIENTE DÍA 1", style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                LinearProgressIndicator(
                                    progress = 0.35f,
                                    modifier = Modifier.fillMaxWidth().height(8.dp),
                                    color = OrangePrimary,
                                    trackColor = Color.White.copy(alpha = 0.1f),
                                    strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                                )
                            }
                        }
                    }

                    // 3. Botones de Selección Horizontales
                    item {
                        Button(
                            modifier = Modifier.fillMaxWidth().height(64.dp),
                            onClick = { viewModel.startWorkout() },
                            colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary, contentColor = Color.Black),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("COMENZAR ENTRENO NUEVO", fontWeight = FontWeight.Black)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            onClick = { viewModel.startFromLastSession() },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.Gray)
                        ) {
                            Text("REPETIR ÚLTIMO", fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            onClick = { showTemplatePicker = true },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.Gray)
                        ) {
                            Text("USAR PLANTILLA", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            } else {
                // --- PANTALLA DE TRABAJO ACTIVO (INTOCABLE) ---
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    item {
                        Surface(
                            color = OrangePrimary.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                        ) {
                            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text("RUTINA ACTIVA: ", style = MaterialTheme.typography.labelLarge, color = OrangePrimary)
                                Text(sessionName.uppercase(), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color.White)
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
                            onShowError = { message -> scope.launch { snackbarHostState.showSnackbar(message) } },
                            onCalculatePlates = { weight, onResult -> calculatorTarget = weight to onResult }
                        )
                    }

                    // --- BOTÓN FINALIZAR CON DISEÑO "STITCH OFFSET" ---
                    item {
                        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp)) {
                            // Viñeta desfasada (Efecto de autor Stitch)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.92f)
                                    .height(60.dp)
                                    .offset(x = (-12).dp, y = 6.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White.copy(alpha = 0.08f))
                            )
                            // Botón real
                            Button(
                                onClick = { viewModel.finishWorkout() },
                                modifier = Modifier.fillMaxWidth().height(60.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = DarkGreySurface),
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, OrangePrimary.copy(alpha = 0.5f))
                            ) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = OrangePrimary)
                                Spacer(modifier = Modifier.width(12.dp))
                                Text("FINALIZAR SESIÓN COMPLETA", color = OrangePrimary, fontWeight = FontWeight.Black, letterSpacing = 1.sp)
                            }
                        }
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

@Composable
fun ExerciseCard(
    exercise: ExerciseEntity,
    viewModel: WorkoutViewModel,
    exerciseSets: List<com.leo2026.weightlifting.data.entity.SetEntryEntity>,
    isResting: Boolean,
    isActive: Boolean,
    onActivate: () -> Unit,
    onDeactivate: () -> Unit,
    onShowError: (String) -> Unit,
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
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (isRunningSet) Color(0xFF1E1E1E) else Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = exercise.name.uppercase(), 
                style = MaterialTheme.typography.titleLarge, 
                fontWeight = FontWeight.Black,
                color = if (isRunningSet) Color(0xFFFF6D00) else Color.Black
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
                    color = Color(0xFFFF6D00)
                )
            }

            if (!isRunningSet) {
                Button(
                    onClick = {
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
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFFFF6D00))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("INICIAR SERIE", color = Color(0xFFFF6D00), fontWeight = FontWeight.Bold)
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
                        Icon(Icons.Default.Stop, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("FINALIZAR", color = Color.White, fontWeight = FontWeight.Bold)
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
                    Icon(Icons.Default.Calculate, contentDescription = null, tint = Color.Black)
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
                        Text("${set.durationMillis / 1000}s", style = MaterialTheme.typography.labelSmall, color = if (isRunningSet) Color(0xFFFF6D00) else Color.Black)
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
        containerColor = Color(0xFF1E1E1E),
        titleContentColor = Color(0xFFFF6D00),
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
            Button(onClick = { onConfirm(name, category, rest.toIntOrNull() ?: 90) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)) { Text("AÑADIR") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("CANCELAR", color = Color(0xFFFF6D00)) }
        }
    )
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
        return@remember result
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF1E1E1E),
        titleContentColor = Color(0xFFFF6D00),
        textContentColor = Color.White,
        title = { Text("CALCULADORA INTELIGENTE") },
        text = {
            Column {
                if (availableBars.isEmpty()) {
                    Text("⚠️ Registra tu equipo", color = Color.Red)
                } else {
                    Text("SOPORTE:", style = MaterialTheme.typography.labelSmall, color = Color(0xFFFF6D00))
                    OutlinedCard(
                        onClick = { expandedBarMenu = true },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        colors = CardDefaults.outlinedCardColors(containerColor = Color(0xFF121212), contentColor = Color.White)
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

                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.3f))
                    Text("DESGLOSE PARA ${currentTarget}kg:", fontWeight = FontWeight.Bold, color = Color(0xFFFF6D00))
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
            Button(onClick = { onConfirm(currentTarget) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)) { Text("OK") }
        }
    )
}

@Composable
fun TemplatePicker(templates: List<com.leo2026.weightlifting.data.entity.TemplateEntity>, onDismiss: () -> Unit, onSelect: (com.leo2026.weightlifting.data.entity.TemplateEntity) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF1E1E1E),
        titleContentColor = Color(0xFFFF6D00),
        title = { Text("SELECCIONAR PLANTILLA") },
        text = {
            LazyColumn {
                items(templates) { template ->
                    TextButton(
                        onClick = { onSelect(template) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(template.name, color = Color.White)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("CANCELAR", color = Color(0xFFFF6D00)) }
        }
    )
}

@Composable
fun SaveTemplateDialog(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF1E1E1E),
        titleContentColor = Color(0xFFFF6D00),
        title = { Text("GUARDAR PLANTILLA") },
        text = {
            TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre de la rutina") })
        },
        confirmButton = {
            Button(onClick = { onConfirm(name) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)) { Text("GUARDAR") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("CANCELAR", color = Color(0xFFFF6D00)) }
        }
    )
}
