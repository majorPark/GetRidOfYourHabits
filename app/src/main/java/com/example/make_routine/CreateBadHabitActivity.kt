package com.example.make_routine

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.make_routine.dao.BadHabitViewModel
import com.example.make_routine.dao.BadHabitViewModelFactory
import com.example.make_routine.model.BadHabit
import java.time.LocalDate

class CreateBadHabitActivity : AppCompatActivity() {
    private val badHabitViewModel: BadHabitViewModel by viewModels {
        (application as BadHabitsApplication).repo?.let { BadHabitViewModelFactory(it) }!!
    }

    private val userBadHabitEditText: EditText by lazy { findViewById(R.id.userBadHabitEditText) }
    private val selectColorSpinner: Spinner by lazy { findViewById(R.id.selectColorSpinner) }
    private var color: String? = null
    @RequiresApi(Build.VERSION_CODES.O)
    var todayDate = LocalDate.now().toString()
    lateinit var badHabitName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_bad_habit)


        badHabitName = userBadHabitEditText.text.toString()


        // 색상 선택 스피너
        selectColorSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(selectColorSpinner.getItemAtPosition(position)) {
                    "gray" -> color = "#AAAAAA"
                    "pink_200" -> color = "#F6ABAB"
                    "pink_500" -> color = "#F89696"
                    "pink_700" -> color = "#F67474"
                    "green_200" -> color = "#DCF88D"
                    "green_500" -> color = "#C4F43C"
                    "green_700" -> color = "#B2F101"
                    "blue_200" -> color = "#8BA9F4"
                    "blue_500" -> color = "#6993FD"
                    "blue_600" -> color = "#4578F8"
                    "teal_200" -> color = "#FF03DAC5"
                    "teal_500" -> color = "#02C3B0"
                    "teal_700" -> color = "#FF018786"
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    fun saveButtonClicked(v: View) {
        badHabitViewModel.insert(BadHabit(badHabitName, todayDate,color))

        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)

        /*
        mainIntent.putExtra("badHabitName", badHabitName)
        mainIntent.putExtra("enrollDate", todayDate)
        mainIntent.putExtra("color", color)
        */

    }
}

