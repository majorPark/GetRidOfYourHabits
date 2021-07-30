package com.example.make_routine.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.make_routine.bad_habit_list.BadHabits

@Dao
interface BadHabitsDao {
    @Query("SELECT* FROM bad_habits")
    fun getAll(): List<BadHabits>

    @Insert
    fun insert(badHabits: BadHabits)

    @Query("DELETE FROM bad_habits")
    fun deleteAll()
}