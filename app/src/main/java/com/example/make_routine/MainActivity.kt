package com.example.make_routine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.make_routine.bad_habit_list.BadHabitListAdapter


class MainActivity : AppCompatActivity() {
    private val todayDateTextView: TextView by lazy {findViewById<TextView>(R.id.today_date)}
    private val mainView: LinearLayout by lazy {findViewById<LinearLayout>(R.id.mainView)}

    private var calendarState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addRoutineButtonClicked(v: View) {}
    fun memoButtonClicked(v: View) {}
    fun calendarButtonClicked(v: View) {}
    fun mainButtonClicked(v: View) {}
    fun weekHistoryButtonClicked(v: View) {}
    fun settingButtonClicked(v: View) {}

    private fun makeRoutineButton(context: String) {
        val routineButton = Button(this).apply {
            text = context
        }
    }

    // 습관 리스트 업데이트. room 데이터로 채우기.
    private fun updateHabitList() {
        badHabitRv.adapter = BadHabitListAdapter(listOf<String>()) // 지금은 빈 리스트를 넣어두었지만, 나중에 room 데이터로 바꿔야 함.
        badHabitRv.layoutManager =
            LinearLayoutManager(this)
        badHabitRv.setHasFixedSize(true)
    }


}