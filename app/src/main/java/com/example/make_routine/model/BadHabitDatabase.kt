package com.example.make_routine.model

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.make_routine.R
import com.example.make_routine.dao.BadHabitDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate

@Database(entities = [BadHabit::class], version = 1)
abstract class BadHabitDatabase: RoomDatabase() {
    abstract fun badHabitDao(): BadHabitDao

    companion object {
        private var INSTANCE: BadHabitDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BadHabitDatabase? {
            if (INSTANCE == null) {
                synchronized(BadHabitDatabase::class) {
                    INSTANCE = databaseBuilder(context.applicationContext, BadHabitDatabase::class.java, "badHabits.db")
                        .addCallback(BadHabitDatabaseCallback(scope))
                        .build()
                }
            }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    private class BadHabitDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { db ->
                scope.launch {
                    var badHabitDao = db.badHabitDao()

                    badHabitDao.deleteAll()

                    val todayDate: LocalDate = LocalDate.now()

                    var badHabit = BadHabit("Bad Habit", "$todayDate", "#AAAAAA")
                    badHabitDao.insert(badHabit)}
            }
        }
    }

    /*
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun populateDatabase(badHabitDao: BadHabitDao) {
        badHabitDao.deleteAll()

        val todayDate: LocalDate = LocalDate.now()

        var badHabit = BadHabit("Bad Habit", "$todayDate")
        badHabitDao.insert(badHabit)
    }

    */
}