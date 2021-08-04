package com.example.make_routine.dao

import androidx.lifecycle.*
import com.example.make_routine.model.BadHabit
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BadHabitViewModel(private val repo: BadHabitRepository): ViewModel() {
    val badHabitNames: LiveData<List<BadHabit>> = repo.badHabitNames.asLiveData()

    fun insert(badHabit: BadHabit) = viewModelScope.launch {
        repo.insert(badHabit)
    }
}

class BadHabitViewModelFactory(private val repo: BadHabitRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BadHabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BadHabitViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}