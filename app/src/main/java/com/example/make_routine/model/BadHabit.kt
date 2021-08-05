package com.example.make_routine.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bad_habits")
data class BadHabit(
    @PrimaryKey val badHabitName: String,
    @ColumnInfo(name = "enroll_date") val enrollDate: String?,
    @ColumnInfo(name = "color") val color: String?
)
