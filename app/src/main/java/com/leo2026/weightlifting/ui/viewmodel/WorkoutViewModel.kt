package com.leo2026.weightlifting.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leo2026.weightlifting.data.entity.*
import com.leo2026.weightlifting.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WorkoutViewModel(private val repository: WorkoutRepository) : ViewModel() {

    private val _currentSessionId = MutableStateFlow<String?>(null)
    val currentSessionId: StateFlow<String?> = _currentSessionId.asStateFlow()

    private val _currentSessionName = MutableStateFlow<String>("Sesión")
    val currentSessionName: StateFlow<String> = _currentSessionName.asStateFlow()

    private val _selectedTemplateId = MutableStateFlow<String?>(null)
    
    private val _manualSessionExerciseIds = MutableStateFlow<List<String>>(emptyList())

    val exercises: StateFlow<List<ExerciseEntity>> = combine(
        _selectedTemplateId,
        _manualSessionExerciseIds,
        _currentSessionId
    ) { templateId, manualIds, sessionId ->
        Triple(templateId, manualIds, sessionId)
    }.flatMapLatest { (templateId, manualIds, sessionId) ->
        if (templateId != null) {
            repository.getExercisesForTemplate(templateId)
        } else if (sessionId != null && manualIds.isNotEmpty()) {
            repository.allExercises.map { all -> all.filter { it.id in manualIds } }
        } else {
            repository.allExercises
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allExercises: StateFlow<List<ExerciseEntity>> = repository.allExercises
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val templates: StateFlow<List<TemplateEntity>> = repository.allTemplates
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val completedSessions: StateFlow<List<WorkoutSessionEntity>> = repository.completedSessions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Training Assets
    val allBars: StateFlow<List<BarEntity>> = repository.allBars
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    val allPlates: StateFlow<List<PlateEntity>> = repository.allPlates
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _newPREvents = MutableStateFlow<String?>(null)
    val newPREvents: StateFlow<String?> = _newPREvents.asStateFlow()

    val currentSets: StateFlow<List<SetEntryEntity>> = _currentSessionId
        .flatMapLatest { sessionId ->
            if (sessionId != null) repository.getSetsForSession(sessionId)
            else flowOf(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getSetsForSession(sessionId: String): Flow<List<SetEntryEntity>> {
        return repository.getSetsForSession(sessionId)
    }

    fun startWorkout(name: String = "Sesión de Entrenamiento", templateId: String? = null) {
        viewModelScope.launch {
            val id = repository.startNewSession(name, templateId)
            _currentSessionName.value = name
            _selectedTemplateId.value = templateId
            _manualSessionExerciseIds.value = emptyList()
            _currentSessionId.value = id
        }
    }

    fun startWorkoutWithExercise(exercise: ExerciseEntity) {
        viewModelScope.launch {
            val id = repository.startNewSession("Entreno: ${exercise.name}")
            _currentSessionName.value = exercise.name
            _selectedTemplateId.value = null
            _manualSessionExerciseIds.value = listOf(exercise.id)
            _currentSessionId.value = id
        }
    }

    fun startFromLastSession() {
        viewModelScope.launch {
            val last = repository.getLastSession()
            if (last != null) {
                startWorkout(last.name, last.templateId)
            } else {
                startWorkout()
            }
        }
    }

    fun finishWorkout() {
        _currentSessionId.value?.let { sessionId ->
            viewModelScope.launch {
                val setsInSession = repository.getSetsForSessionList(sessionId)
                if (setsInSession.isNotEmpty()) {
                    repository.finishSession(sessionId)
                }
                _currentSessionId.value = null
                _selectedTemplateId.value = null
                _manualSessionExerciseIds.value = emptyList()
                _currentSessionName.value = "Sesión"
            }
        }
    }

    fun saveCurrentAsTemplate(name: String): Boolean {
        val exists = templates.value.any { it.name.trim().equals(name.trim(), ignoreCase = true) }
        if (exists) return false

        val sessionId = _currentSessionId.value ?: return false
        viewModelScope.launch {
            val exerciseIds = currentSets.value.map { it.exerciseId }.distinct()
            repository.saveAsTemplate(name.trim(), exerciseIds)
        }
        return true
    }

    fun updateTemplateName(templateId: String, newName: String): Boolean {
        val exists = templates.value.any { 
            it.id != templateId && it.name.trim().equals(newName.trim(), ignoreCase = true) 
        }
        if (exists) return false

        viewModelScope.launch {
            repository.updateTemplateName(templateId, newName.trim())
        }
        return true
    }

    fun deleteTemplate(templateId: String) {
        viewModelScope.launch {
            repository.deleteTemplate(templateId)
        }
    }

    fun updateExercise(exercise: ExerciseEntity): Boolean {
        val exists = allExercises.value.any { 
            it.id != exercise.id && it.name.trim().equals(exercise.name.trim(), ignoreCase = true) 
        }
        if (exists) return false

        viewModelScope.launch {
            repository.updateExercise(exercise.copy(name = exercise.name.trim(), category = exercise.category.trim()))
        }
        return true
    }

    fun deleteExercise(exercise: ExerciseEntity) {
        viewModelScope.launch {
            repository.deleteExercise(exercise)
        }
    }

    // Asset management
    fun addBar(name: String, weight: Double) {
        viewModelScope.launch {
            repository.insertBar(BarEntity(java.util.UUID.randomUUID().toString(), name, weight))
        }
    }

    fun deleteBar(bar: BarEntity) {
        viewModelScope.launch {
            repository.deleteBar(bar)
        }
    }

    fun addPlate(weight: Double, quantity: Int) {
        viewModelScope.launch {
            repository.insertPlate(PlateEntity(java.util.UUID.randomUUID().toString(), weight, quantity))
        }
    }

    fun deletePlate(plate: PlateEntity) {
        viewModelScope.launch {
            repository.deletePlate(plate)
        }
    }

    // --- MEJORADO: Búsqueda segura del ejercicio para el Snapshot ---
    fun addSet(exerciseId: String, weight: Double, reps: Int, restSeconds: Int, durationMillis: Long) {
        val sessionId = _currentSessionId.value ?: return
        viewModelScope.launch {
            // Buscamos el ejercicio directamente en la DB para asegurar que tenemos sus datos
            val exercise = repository.getExerciseById(exerciseId)
            val currentPR = repository.getPersonalRecord(exerciseId)
            
            val set = SetEntryEntity(
                sessionId = sessionId,
                exerciseId = exerciseId,
                weight = weight,
                reps = reps,
                timestamp = System.currentTimeMillis(),
                exerciseNameSnapshot = exercise?.name ?: "Desconocido",
                exerciseCategorySnapshot = exercise?.category ?: "General",
                exerciseRestSnapshot = restSeconds,
                durationMillis = durationMillis
            )
            repository.addSet(set)

            if (weight > currentPR && currentPR > 0) {
                _newPREvents.value = "¡Nuevo Récord Personal! ${weight}kg"
            }

            _timerTrigger.value = restSeconds
        }
    }

    fun clearPREvent() {
        _newPREvents.value = null
    }

    private val _timerTrigger = MutableStateFlow<Int?>(null)
    val timerTrigger: StateFlow<Int?> = _timerTrigger.asStateFlow()

    fun onTimerStarted() {
        _timerTrigger.value = null
    }

    private val backupManager = com.leo2026.weightlifting.data.backup.BackupManager()

    suspend fun exportData(): String {
        val bundle = repository.getBackupBundle()
        return backupManager.createJsonBackup(bundle)
    }

    fun importData(json: String, onComplete: () -> Unit) {
        val bundle = backupManager.restoreFromJson(json)
        if (bundle != null) {
            viewModelScope.launch {
                repository.restoreBackupBundle(bundle)
                onComplete()
            }
        }
    }

    fun addExercise(name: String, category: String, restSeconds: Int): Boolean {
        val exists = allExercises.value.any { it.name.trim().equals(name.trim(), ignoreCase = true) }
        if (exists) return false

        viewModelScope.launch {
            repository.insertExercise(
                ExerciseEntity(
                    id = java.util.UUID.randomUUID().toString(),
                    name = name.trim(),
                    category = category.trim(),
                    defaultRestSeconds = restSeconds
                )
            )
        }
        return true
    }
}
