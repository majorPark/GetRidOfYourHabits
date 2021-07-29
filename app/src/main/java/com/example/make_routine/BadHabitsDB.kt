package com.example.make_routine

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.make_routine.bad_habit_list.BadHabits
import com.example.make_routine.dao.BadHabitsDao

@Database(entities = [BadHabits::class], version = 1)
abstract class BadHabitsDB: RoomDatabase() {
    abstract fun badHabitsDao(): BadHabitsDao

    companion object {
        private var INSTANCE: BadHabitsDB? = null

        fun getInstance(context: Context): BadHabitsDB? {
            if (INSTANCE == null) {
                synchronized(BadHabitsDB::class) {
                    INSTANCE = databaseBuilder(context.applicationContext, BadHabitsDB::class.java, "badHabits.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}