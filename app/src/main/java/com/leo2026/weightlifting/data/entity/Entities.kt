package com.leo2026.weightlifting.data.entity

import androidx.room.*

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String, // Chest, Back, etc.
    val defaultRestSeconds: Int = 90,
    val isDeleted: Boolean = false
)

@Entity(tableName = "workout_sessions")
data class WorkoutSessionEntity(
    @PrimaryKey val id: String,
    val startTime: Long,
    val endTime: Long? = null,
    val name: String,
    val templateId: String? = null
)

@Entity(
    tableName = "set_entries",
    foreignKeys = [
        ForeignKey(
            entity = WorkoutSessionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("sessionId"), Index("exerciseId")]
)
data class SetEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionId: String,
    val exerciseId: String,
    val weight: Double,
    val reps: Int,
    val setType: String = "WORKING", // WORKING, WARMUP, DROP, AMRAP
    val timestamp: Long,
    val rpe: Int? = null,

    // Snapshots
    val exerciseNameSnapshot: String = "",
    val exerciseCategorySnapshot: String = "",
    val exerciseRestSnapshot: Int = 0,

    // --- NUEVO: Duración de la serie ---
    val durationMillis: Long = 0
)

@Entity(tableName = "templates")
data class TemplateEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String? = null,
    val isDeleted: Boolean = false
)

@Entity(
    tableName = "template_exercises",
    foreignKeys = [
        ForeignKey(
            entity = TemplateEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["templateId", "exerciseId", "orderIndex"]
)
data class TemplateExerciseEntity(
    val templateId: String,
    val exerciseId: String,
    val orderIndex: Int
)

// --- TRAINING ASSETS ---

@Entity(tableName = "bars")
data class BarEntity(
    @PrimaryKey val id: String,
    val name: String,
    val weight: Double
)

@Entity(tableName = "plates")
data class PlateEntity(
    @PrimaryKey val id: String,
    val weight: Double,
    val quantity: Int
)
