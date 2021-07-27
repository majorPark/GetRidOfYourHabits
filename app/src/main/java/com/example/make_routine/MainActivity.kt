package com.example.make_routine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val todayDateTextView: TextView by lazy {findViewById<TextView>(R.id.today_date)}
    private val routineList: LinearLayout by lazy {findViewById<LinearLayout>(R.id.routineList)}

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

    private fun makeRoutineButton (context: String) {
        val routineButton = Button(this).apply {
            text = context
        }
    }
}