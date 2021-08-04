package com.example.make_routine.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.make_routine.model.BadHabit
import kotlinx.coroutines.flow.Flow

@Dao
interface BadHabitDao {
    @Query("SELECT* FROM bad_habits")
    fun getAll(): Flow<List<BadHabit>>

    @Insert
    suspend fun insert(badHabit: BadHabit)

    @Query("DELETE FROM bad_habits")
    suspend fun deleteAll()
}