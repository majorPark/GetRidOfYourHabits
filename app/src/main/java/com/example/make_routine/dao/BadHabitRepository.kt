package com.example.make_routine.dao

import androidx.annotation.WorkerThread
import com.example.make_routine.model.BadHabit
import kotlinx.coroutines.flow.Flow

class BadHabitRepository(private val badHabitDao: BadHabitDao) {
    val badHabitNames: Flow<List<BadHabit>> = badHabitDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert (badHabit: BadHabit) {
        badHabitDao.insert(badHabit)
    }
}