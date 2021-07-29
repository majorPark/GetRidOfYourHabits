package com.example.make_routine.bad_habit_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bad_habits")
data class BadHabits(
    @PrimaryKey val badHabitName: String,
    @ColumnInfo(name = "enroll_date") val enrollDate: String,
    // @ColumnInfo(name = "color") val color: String
)
