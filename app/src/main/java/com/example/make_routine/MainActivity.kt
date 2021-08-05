package com.example.make_routine

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.make_routine.dao.BadHabitViewModel
import com.example.make_routine.dao.BadHabitViewModelFactory
import com.example.make_routine.model.BadHabit
import com.example.make_routine.adapter.BadHabitAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private val badHabitViewModel: BadHabitViewModel by viewModels {
        (application as BadHabitsApplication).repo?.let { BadHabitViewModelFactory(it) }!!
    }

    private val todayDateTextView: TextView by lazy { findViewById(R.id.today_date) }
    private val addRoutineFab: FloatingActionButton by lazy { findViewById(R.id.addRoutineFab) }
    // private val addRoutineBtn: ImageButton by lazy { findViewById(R.id.addRoutineBtn) }
    private val calendarBtn: ImageButton by lazy { findViewById(R.id.calendarBtn) }


    private val badHabitListRecyclerView: RecyclerView by lazy { findViewById(R.id.badHabitListRecyclerView) }
    private val adapter = BadHabitAdapter()

    private var todayDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        badHabitListRecyclerView.adapter = adapter
        badHabitListRecyclerView.layoutManager = LinearLayoutManager(this)

        badHabitViewModel.badHabitNames.observe(this) {badHabitName ->
            badHabitName.let {adapter.replaceTo(it)}
        }

        /*
        // database thread 시
        Thread {
            badHabitList = badHabitDb?.badHabitDao()?.getAll()!!

            var badHabitsAdapter = BadHabitsAdapter(this, badHabitList)
            badHabitsAdapter.notifyDataSetChanged()
            habitListRecyclerView.adapter = badHabitsAdapter
            habitListRecyclerView.layoutManager = LinearLayoutManager(this)
            habitListRecyclerView.setHasFixedSize(true)
        }.start()
        */

        // todayDateTextView 오늘 날짜로 설정하기
        val selectedDateCalendar =
            Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        var selectedDateString = dateFormat.format(selectedDateCalendar.time)
        todayDateTextView.text = selectedDateString
        todayDate = selectedDateString.toString()

        // 달력 변경 리스너
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                // 지정한 날짜 변경
                selectedDateCalendar.set(Calendar.YEAR, year)
                selectedDateCalendar.set(Calendar.MONTH, month)
                selectedDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // 날짜 텍스트뷰 업데이트
                selectedDateString = dateFormat.format(selectedDateCalendar.time)
                todayDateTextView.text = selectedDateString

                // 날짜 변경 알림창
                Toast.makeText(this, "날짜: $selectedDateString", Toast.LENGTH_SHORT).show()

                // 습관 리스트 업데이트
                // updateHabitListByDate
            }

        // 달력 다이얼로그
        val datePicker = DatePickerDialog(
            this,
            dateSetListener,
            selectedDateCalendar.get(Calendar.YEAR),
            selectedDateCalendar.get(Calendar.MONTH),
            selectedDateCalendar.get(Calendar.DAY_OF_MONTH)
        )

        // 달력 아이콘 버튼 클릭 시 달력 다이어로그를 띄움.
        calendarBtn.setOnClickListener {
            datePicker.show()
        }

        addRoutineFab.setOnClickListener {
            val createBadHabitIntent = Intent(this, CreateBadHabitActivity::class.java)
            startActivity(createBadHabitIntent)

            /*
            Thread({
                badHabitDb?.badHabitsDao()?.insert(
                    BadHabit(
                        badHabitName = badHabitName.toString(),
                        enrollDate = todayDate.toString()
                    )
                )
            }).start()
            */
        }
    }

    /*
    private fun showInputDialog() {
        val input = EditText(this)
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)

        input.hint = "Bad habit's name"
        input.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle("Input your bad habit.")
            .setView(input)
            .setPositiveButton("Save", DialogInterface.OnClickListener { _, _ ->
                var badHabitsName = input.text.toString()
                badHabitsName})
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ -> })
            .show()
    }
    */

    // 습관 리스트 업데이트.
    // 지금은 빈 리스트를 넣어두었는데, 나중에 room 데이터로 바꿔야 함.
    // 날짜를 입력값으로 받고, 해당 데이터를 불러오도록 수정해야 할 듯.
    /*    private fun updateHabitList() {
        habitListRecyclerView.adapter = BadHabitsAdapter(listOf<String>())
        habitListRecyclerView.layoutManager =
            LinearLayoutManager(this)
        habitListRecyclerView.setHasFixedSize(true)
    } */

}