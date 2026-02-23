package com.leo2026.weightlifting.data.dao

import androidx.room.*
import com.leo2026.weightlifting.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises WHERE isDeleted = 0 ORDER BY name ASC")
    fun getAllExercises(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE id = :id LIMIT 1")
    suspend fun getExerciseById(id: String): ExerciseEntity?

    @Query("SELECT * FROM exercises")
    suspend fun getAllExercisesList(): List<ExerciseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity)

    @Update
    suspend fun updateExercise(exercise: ExerciseEntity)

    @Query("UPDATE exercises SET isDeleted = 1 WHERE id = :exerciseId")
    suspend fun softDeleteExercise(exerciseId: String)

    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)
}

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout_sessions")
    suspend fun getAllSessionsList(): List<WorkoutSessionEntity>

    @Query("SELECT * FROM set_entries")
    suspend fun getAllSetsList(): List<SetEntryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSessions(sessions: List<WorkoutSessionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSets(sets: List<SetEntryEntity>)

    @Query("SELECT * FROM workout_sessions WHERE endTime IS NOT NULL ORDER BY startTime DESC")
    fun getCompletedSessions(): Flow<List<WorkoutSessionEntity>>

    @Query("SELECT * FROM workout_sessions ORDER BY startTime DESC LIMIT 1")
    suspend fun getLatestSession(): WorkoutSessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: WorkoutSessionEntity)

    @Query("UPDATE workout_sessions SET endTime = :endTime WHERE id = :sessionId")
    suspend fun endSession(sessionId: String, endTime: Long)

    @Transaction
    @Query("SELECT * FROM set_entries WHERE sessionId = :sessionId")
    fun getSetsForSession(sessionId: String): Flow<List<SetEntryEntity>>

    @Query("SELECT * FROM set_entries WHERE sessionId = :sessionId")
    suspend fun getSetsForSessionList(sessionId: String): List<SetEntryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSet(set: SetEntryEntity)

    @Delete
    suspend fun deleteSet(set: SetEntryEntity)

    @Query("SELECT * FROM set_entries WHERE exerciseId = :exerciseId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastSetForExercise(exerciseId: String): SetEntryEntity?

    @Query("SELECT MAX(weight) FROM set_entries WHERE exerciseId = :exerciseId")
    suspend fun getPersonalRecord(exerciseId: String): Double?
}

@Dao
interface TemplateDao {
    @Query("SELECT * FROM templates WHERE isDeleted = 0")
    fun getAllTemplates(): Flow<List<TemplateEntity>>

    @Query("SELECT * FROM templates")
    suspend fun getAllTemplatesList(): List<TemplateEntity>

    @Query("SELECT * FROM template_exercises")
    suspend fun getAllTemplateExercisesList(): List<TemplateExerciseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemplates(templates: List<TemplateEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemplateExercises(templateExercises: List<TemplateExerciseEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemplate(template: TemplateEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemplateExercise(templateExercise: TemplateExerciseEntity)

    @Query("UPDATE templates SET name = :newName WHERE id = :templateId")
    suspend fun updateTemplateName(templateId: String, newName: String)

    @Query("UPDATE templates SET isDeleted = 1 WHERE id = :templateId")
    suspend fun softDeleteTemplate(templateId: String)

    @Query("DELETE FROM templates WHERE id = :templateId")
    suspend fun deleteTemplateById(templateId: String)

    @Query("DELETE FROM template_exercises WHERE templateId = :templateId")
    suspend fun deleteExercisesFromTemplate(templateId: String)

    @Query("""
        SELECT e.* FROM exercises e
        INNER JOIN template_exercises te ON e.id = te.exerciseId
        WHERE te.templateId = :templateId
        ORDER BY te.orderIndex
    """)
    fun getExercisesForTemplate(templateId: String): Flow<List<ExerciseEntity>>

    @Transaction
    @Query("SELECT * FROM templates WHERE id = :templateId")
    suspend fun getTemplateWithExercises(templateId: String): TemplateEntity?
}

@Dao
interface AssetDao {
    @Query("SELECT * FROM bars ORDER BY weight DESC")
    fun getAllBars(): Flow<List<BarEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBar(bar: BarEntity)

    @Delete
    suspend fun deleteBar(bar: BarEntity)

    @Query("SELECT * FROM plates ORDER BY weight DESC")
    fun getAllPlates(): Flow<List<PlateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlate(plate: PlateEntity)

    @Delete
    suspend fun deletePlate(plate: PlateEntity)
}
