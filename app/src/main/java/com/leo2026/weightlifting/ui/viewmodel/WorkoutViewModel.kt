package com.leo2026.weightlifting.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leo2026.weightlifting.data.entity.*
import com.leo2026.weightlifting.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SessionSummary(
    val names: String,
    val categories: String
)

class WorkoutViewModel(private val repository: WorkoutRepository) : ViewModel() {

    private val _currentSessionId = MutableStateFlow<String?>(null)
    val currentSessionId: StateFlow<String?> = _currentSessionId.asStateFlow()

    private val _currentSessionName = MutableStateFlow<String>("Sesión")
    val currentSessionName: StateFlow<String> = _currentSessionName.asStateFlow()

    private val _selectedTemplateId = MutableStateFlow<String?>(null)
    
    private val _manualSessionExerciseIds = MutableStateFlow<List<String>>(emptyList())

    val userProfile: StateFlow<UserEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private val _showPartialTemplateDialog = MutableStateFlow<List<String>?>(null)
    val showPartialTemplateDialog: StateFlow<List<String>?> = _showPartialTemplateDialog.asStateFlow()

    @kotlinx.coroutines.ExperimentalCoroutinesApi
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

    val allBars: StateFlow<List<BarEntity>> = repository.allBars
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    val allPlates: StateFlow<List<PlateEntity>> = repository.allPlates
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _newPREvents = MutableStateFlow<String?>(null)
    val newPREvents: StateFlow<String?> = _newPREvents.asStateFlow()

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    val currentSets: StateFlow<List<SetEntryEntity>> = _currentSessionId
        .flatMapLatest { sessionId ->
            if (sessionId != null) repository.getSetsForSession(sessionId)
            else flowOf(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateProfile(firstName: String, lastName: String) {
        viewModelScope.launch {
            repository.insertUser(UserEntity(firstName = firstName, lastName = lastName))
        }
    }

    fun getSetsForSession(sessionId: String): Flow<List<SetEntryEntity>> {
        return repository.getSetsForSession(sessionId)
    }

    fun getSessionSummary(sessionId: String): Flow<SessionSummary> {
        return repository.getSetsForSession(sessionId).map { sets ->
            if (sets.isEmpty()) return@map SessionSummary("Vacía", "General")
            val names = sets.map { it.exerciseNameSnapshot }.distinct().joinToString(", ")
            val categories = sets.map { it.exerciseCategorySnapshot }.distinct().joinToString(", ")
            SessionSummary(names, categories)
        }
    }

    fun resetToWorkoutLauncher() {
        _currentSessionId.value = null
        _selectedTemplateId.value = null
        _manualSessionExerciseIds.value = emptyList()
        _currentSessionName.value = "Sesión"
        _showPartialTemplateDialog.value = null
    }

    fun saveRoutine(name: String, exerciseIds: List<String>, existingId: String? = null) {
        viewModelScope.launch {
            repository.saveAsTemplate(name, exerciseIds, existingId)
        }
    }

    fun getExerciseIdsForTemplate(templateId: String): Flow<List<String>> {
        return repository.getExercisesForTemplate(templateId).map { list -> list.map { it.id } }
    }

    suspend fun addExerciseAndGetId(name: String, category: String, restSeconds: Int): String? {
        val exists = allExercises.value.any { it.name.trim().equals(name.trim(), ignoreCase = true) }
        if (exists) return null
        val id = java.util.UUID.randomUUID().toString()
        repository.insertExercise(ExerciseEntity(id = id, name = name.trim(), category = category.trim(), defaultRestSeconds = restSeconds))
        return id
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
            val allCompleted = completedSessions.value
            val lastEffective = allCompleted.firstOrNull()
            if (lastEffective != null) {
                when (lastEffective.sourceType) {
                    "TEMPLATE" -> startWorkout(lastEffective.name, lastEffective.templateId)
                    "SINGLE_EXERCISE" -> {
                        val exercise = lastEffective.singleExerciseId?.let { repository.getExerciseById(it) }
                        if (exercise != null) startWorkoutWithExercise(exercise) else startWorkout()
                    }
                    else -> startWorkout()
                }
            } else {
                startWorkout()
            }
        }
    }

    // --- RE-IMPLEMENTADO: Obtener el último set histórico para pre-llenado ---
    suspend fun getLastSetForExercise(exerciseId: String): SetEntryEntity? {
        return repository.getLastSetForExercise(exerciseId)
    }

    // --- RE-IMPLEMENTADO: Validar peso contra inventario ---
    fun isWeightPossible(targetWeight: Double): Boolean {
        val bars = allBars.value
        val plates = allPlates.value
        if (bars.isEmpty()) return false
        
        return bars.any { bar ->
            val remaining = targetWeight - bar.weight
            if (remaining < 0) return@any false
            if (remaining == 0.0) return@any true
            
            val remainingPerSide = remaining / 2.0
            var currentTarget = remainingPerSide
            val sortedPlates = plates.sortedByDescending { it.weight }
            for (plate in sortedPlates) {
                val maxPairs = plate.quantity / 2
                var pairsUsed = 0
                while (currentTarget >= plate.weight - 0.001 && pairsUsed < maxPairs) {
                    currentTarget -= plate.weight
                    pairsUsed++
                }
            }
            currentTarget < 0.001
        }
    }

    fun finishWorkout() {
        _currentSessionId.value?.let { sessionId ->
            viewModelScope.launch {
                val setsInSession = repository.getSetsForSessionList(sessionId)
                if (setsInSession.isNotEmpty()) {
                    repository.finishSession(sessionId)
                    val templateId = _selectedTemplateId.value
                    if (templateId != null) {
                        val originalExercises = repository.getExercisesForTemplate(templateId).first()
                        val exercisesDoneIds = setsInSession.map { it.exerciseId }.distinct()
                        if (exercisesDoneIds.size < originalExercises.size && exercisesDoneIds.isNotEmpty()) {
                            _showPartialTemplateDialog.value = exercisesDoneIds
                            return@launch
                        }
                    }
                }
                resetToWorkoutLauncher()
            }
        }
    }

    fun dismissPartialDialog() {
        resetToWorkoutLauncher()
    }

    fun finishWorkoutAndSavePartial(name: String) {
        val exerciseIds = _showPartialTemplateDialog.value ?: return
        viewModelScope.launch {
            repository.saveAsTemplate(name, exerciseIds)
            resetToWorkoutLauncher()
        }
    }

    fun updateTemplateName(templateId: String, newName: String): Boolean {
        val exists = templates.value.any { it.id != templateId && it.name.trim().equals(newName.trim(), ignoreCase = true) }
        if (exists) return false
        viewModelScope.launch { repository.updateTemplateName(templateId, newName.trim()) }
        return true
    }

    fun deleteTemplate(templateId: String) { viewModelScope.launch { repository.deleteTemplate(templateId) } }
    fun updateExercise(exercise: ExerciseEntity): Boolean {
        val exists = allExercises.value.any { it.id != exercise.id && it.name.trim().equals(exercise.name.trim(), ignoreCase = true) }
        if (exists) return false
        viewModelScope.launch { repository.updateExercise(exercise.copy(name = exercise.name.trim(), category = exercise.category.trim())) }
        return true
    }
    fun deleteExercise(exercise: ExerciseEntity) { viewModelScope.launch { repository.deleteExercise(exercise) } }
    fun addBar(name: String, weight: Double) { viewModelScope.launch { repository.insertBar(BarEntity(java.util.UUID.randomUUID().toString(), name, weight)) } }
    fun deleteBar(bar: BarEntity) { viewModelScope.launch { repository.deleteBar(bar) } }
    fun addPlate(weight: Double, quantity: Int) { viewModelScope.launch { repository.insertPlate(PlateEntity(java.util.UUID.randomUUID().toString(), weight, quantity)) } }
    fun deletePlate(plate: PlateEntity) { viewModelScope.launch { repository.deletePlate(plate) } }

    fun addSet(exerciseId: String, weight: Double, reps: Int, restSeconds: Int, durationMillis: Long) {
        val sessionId = _currentSessionId.value ?: return
        viewModelScope.launch {
            val exercise = repository.getExerciseById(exerciseId)
            val currentPR = repository.getPersonalRecord(exerciseId)
            val set = SetEntryEntity(sessionId = sessionId, exerciseId = exerciseId, weight = weight, reps = reps, timestamp = System.currentTimeMillis(), exerciseNameSnapshot = exercise?.name ?: "Desconocido", exerciseCategorySnapshot = exercise?.category ?: "General", exerciseRestSnapshot = restSeconds, durationMillis = durationMillis)
            repository.addSet(set)
            if (weight > currentPR && currentPR > 0) { _newPREvents.value = "¡Nuevo Récord Personal! ${weight}kg" }
            _timerTrigger.value = restSeconds
        }
    }

    fun clearPREvent() { _newPREvents.value = null }
    private val _timerTrigger = MutableStateFlow<Int?>(null)
    val timerTrigger: StateFlow<Int?> = _timerTrigger.asStateFlow()
    fun onTimerStarted() { _timerTrigger.value = null }
    private val backupManager = com.leo2026.weightlifting.data.backup.BackupManager()
    suspend fun exportData(): String { return backupManager.createJsonBackup(repository.getBackupBundle()) }
    fun importData(json: String, onComplete: () -> Unit) { val bundle = backupManager.restoreFromJson(json); if (bundle != null) { viewModelScope.launch { repository.restoreBackupBundle(bundle); onComplete() } } }
    fun addExercise(name: String, category: String, restSeconds: Int): Boolean {
        val exists = allExercises.value.any { it.name.trim().equals(name.trim(), ignoreCase = true) }
        if (exists) return false
        viewModelScope.launch { repository.insertExercise(ExerciseEntity(id = java.util.UUID.randomUUID().toString(), name = name.trim(), category = category.trim(), defaultRestSeconds = restSeconds)) }
        return true
    }
}
