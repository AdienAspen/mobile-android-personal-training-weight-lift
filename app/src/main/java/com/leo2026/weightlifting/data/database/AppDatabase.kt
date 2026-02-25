package com.leo2026.weightlifting.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leo2026.weightlifting.data.dao.*
import com.leo2026.weightlifting.data.entity.*

@Database(
    entities = [
        ExerciseEntity::class,
        WorkoutSessionEntity::class,
        SetEntryEntity::class,
        TemplateEntity::class,
        TemplateExerciseEntity::class,
        BarEntity::class,
        PlateEntity::class,
        UserEntity::class
    ],
    version = 6, // Increment version for UserEntity
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun templateDao(): TemplateDao
    abstract fun assetDao(): AssetDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "leo_2026_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
