package com.example.make_routine

import android.app.Application
import com.example.make_routine.dao.BadHabitRepository
import com.example.make_routine.model.BadHabitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BadHabitsApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val db by lazy { BadHabitDatabase.getDatabase(this, applicationScope) }
    val repo by lazy { db?.let { BadHabitRepository(it.badHabitDao()) } }
}