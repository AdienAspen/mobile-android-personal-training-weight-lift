package com.leo2026.weightlifting.data.repository

import com.leo2026.weightlifting.data.dao.*
import com.leo2026.weightlifting.data.entity.*
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(
    private val exerciseDao: ExerciseDao,
    private val workoutDao: WorkoutDao,
    private val templateDao: TemplateDao,
    private val assetDao: AssetDao
) {
    val allExercises: Flow<List<ExerciseEntity>> = exerciseDao.getAllExercises()
    val completedSessions: Flow<List<WorkoutSessionEntity>> = workoutDao.getCompletedSessions()
    val allTemplates: Flow<List<TemplateEntity>> = templateDao.getAllTemplates()
    
    // Training Assets
    val allBars: Flow<List<BarEntity>> = assetDao.getAllBars()
    val allPlates: Flow<List<PlateEntity>> = assetDao.getAllPlates()

    suspend fun insertBar(bar: BarEntity) = assetDao.insertBar(bar)
    suspend fun deleteBar(bar: BarEntity) = assetDao.deleteBar(bar)
    suspend fun insertPlate(plate: PlateEntity) = assetDao.insertPlate(plate)
    suspend fun deletePlate(plate: PlateEntity) = assetDao.deletePlate(plate)

    suspend fun insertExercise(exercise: ExerciseEntity) {
        exerciseDao.insertExercise(exercise)
    }

    suspend fun getExerciseById(id: String): ExerciseEntity? {
        return exerciseDao.getExerciseById(id)
    }

    suspend fun updateExercise(exercise: ExerciseEntity) {
        exerciseDao.updateExercise(exercise)
    }

    suspend fun deleteExercise(exercise: ExerciseEntity) {
        exerciseDao.softDeleteExercise(exercise.id)
    }

    suspend fun startNewSession(name: String, templateId: String? = null): String {
        val id = java.util.UUID.randomUUID().toString()
        val session = WorkoutSessionEntity(
            id = id,
            startTime = System.currentTimeMillis(),
            name = name,
            templateId = templateId
        )
        workoutDao.insertSession(session)
        return id
    }

    suspend fun finishSession(sessionId: String) {
        workoutDao.endSession(sessionId, System.currentTimeMillis())
    }

    suspend fun saveAsTemplate(name: String, exerciseIds: List<String>) {
        val templateId = java.util.UUID.randomUUID().toString()
        templateDao.insertTemplate(TemplateEntity(templateId, name))
        exerciseIds.forEachIndexed { index, exerciseId ->
            templateDao.insertTemplateExercise(
                TemplateExerciseEntity(templateId, exerciseId, index)
            )
        }
    }

    suspend fun updateTemplateName(templateId: String, newName: String) {
        templateDao.updateTemplateName(templateId, newName)
    }

    suspend fun deleteTemplate(templateId: String) {
        templateDao.softDeleteTemplate(templateId)
    }

    suspend fun getLastSession(): WorkoutSessionEntity? {
        return workoutDao.getLatestSession()
    }

    fun getExercisesForTemplate(templateId: String): Flow<List<ExerciseEntity>> {
        return templateDao.getExercisesForTemplate(templateId)
    }

    fun getSetsForSession(sessionId: String): Flow<List<SetEntryEntity>> {
        return workoutDao.getSetsForSession(sessionId)
    }

    suspend fun getSetsForSessionList(sessionId: String): List<SetEntryEntity> {
        return workoutDao.getSetsForSessionList(sessionId)
    }

    suspend fun addSet(set: SetEntryEntity) {
        workoutDao.insertSet(set)
    }

    suspend fun getLastSetForExercise(exerciseId: String): SetEntryEntity? {
        return workoutDao.getLastSetForExercise(exerciseId)
    }

    suspend fun getPersonalRecord(exerciseId: String): Double {
        return workoutDao.getPersonalRecord(exerciseId) ?: 0.0
    }

    suspend fun getBackupBundle(): com.leo2026.weightlifting.data.backup.BackupBundle {
        return com.leo2026.weightlifting.data.backup.BackupBundle(
            exercises = exerciseDao.getAllExercisesList(),
            sessions = workoutDao.getAllSessionsList(),
            sets = workoutDao.getAllSetsList(),
            templates = templateDao.getAllTemplatesList(),
            templateExercises = templateDao.getAllTemplateExercisesList()
        )
    }

    suspend fun restoreBackupBundle(bundle: com.leo2026.weightlifting.data.backup.BackupBundle) {
        exerciseDao.insertExercises(bundle.exercises)
        workoutDao.insertSessions(bundle.sessions)
        workoutDao.insertSets(bundle.sets)
        templateDao.insertTemplates(bundle.templates)
        templateDao.insertTemplateExercises(bundle.templateExercises)
    }
}
