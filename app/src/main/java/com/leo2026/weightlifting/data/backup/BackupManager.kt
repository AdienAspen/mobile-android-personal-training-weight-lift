package com.leo2026.weightlifting.data.backup

import com.google.gson.Gson
import com.leo2026.weightlifting.data.entity.*

data class BackupBundle(
    val exercises: List<ExerciseEntity>,
    val sessions: List<WorkoutSessionEntity>,
    val sets: List<SetEntryEntity>,
    val templates: List<TemplateEntity>,
    val templateExercises: List<TemplateExerciseEntity>
)

class BackupManager {
    private val gson = Gson()

    fun createJsonBackup(bundle: BackupBundle): String {
        return gson.toJson(bundle)
    }

    fun restoreFromJson(json: String): BackupBundle? {
        return try {
            gson.fromJson(json, BackupBundle::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
