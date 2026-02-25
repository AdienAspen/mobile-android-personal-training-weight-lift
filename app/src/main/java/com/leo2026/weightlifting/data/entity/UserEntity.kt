package com.leo2026.weightlifting.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserEntity(
    @PrimaryKey val id: Int = 1, // Solo permitiremos un perfil por ahora
    val firstName: String,
    val lastName: String,
    val age: Int? = null,
    val weight: Double? = null,
    val height: Double? = null
)
