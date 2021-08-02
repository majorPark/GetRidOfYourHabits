package com.example.make_routine

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.make_routine.bad_habit_list.BadHabits
import com.example.make_routine.bad_habit_list.BadHabitsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    // 클래스 안에서 사용할 변수들을 미리 할당.
    private var badHabitsDB: BadHabitsDB? = null
    private var badHabitsList = listOf<BadHabits>()
    private var todayDate: String? = null

    // 클래스 안에서 사용할 뷰들을 미리 할당.
    private val todayDateTextView: TextView by lazy { findViewById(R.id.today_date) }
    private val addRoutineBtn: ImageButton by lazy { findViewById(R.id.addRoutineBtn) }
    private val calendarBtn: ImageButton by lazy { findViewById(R.id.calendarBtn) }
    private val addRoutineFab: FloatingActionButton = findViewById(R.id.addRoutineFab)
    private val habitListRecyclerView: RecyclerView by lazy { findViewById(R.id.habitListRecyclerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 룸 데이터베이스에 저장된 나쁜 습관 리스트 불러오기.
        badHabitsDB = BadHabitsDB.getInstance(this)

        // 불러온 나쁜 습관 리스트를 리사이클러뷰에 띄우기.
        Thread {
            badHabitsList = badHabitsDB?.badHabitsDao()?.getAll()!!

            val badHabitsAdapter = BadHabitsAdapter(this, badHabitsList)
            badHabitsAdapter.notifyDataSetChanged()
            habitListRecyclerView.adapter = badHabitsAdapter
            habitListRecyclerView.layoutManager = LinearLayoutManager(this)
            habitListRecyclerView.setHasFixedSize(true)
        }.start()

        // todayDateTextView 오늘 날짜로 설정.
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

        // 루틴 추가 버튼을 눌렀을 때 추가 다이얼로그 띄움.
        addRoutineBtn.setOnClickListener {
            var badHabitName = showInputDialog()

            Thread {
                badHabitsDB?.badHabitsDao()?.insert(
                    BadHabits(
                        badHabitName = badHabitName.toString(),
                        enrollDate = todayDate.toString()
                    )
                )
            }.start()
        }

        // 루틴 추가 버튼을 눌렀을 때 추가 다이얼로그 띄움. (Floating Action Button)
        addRoutineFab.setOnClickListener { view ->
            var badHabitName = showInputDialog()

            Thread {
                badHabitsDB?.badHabitsDao()?.insert(
                    BadHabits(
                        badHabitName = badHabitName.toString(),
                        enrollDate = todayDate.toString()
                    )
                )
            }.start()
        }
    }

    // 습관 입력 다이얼로그의 기능을 정의하는 함수.
    private fun showInputDialog() {
        val input = EditText(this)
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)

        input.hint = "Bad habit's name"
        input.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle("Input your bad habit.")
            .setView(input)
            .setPositiveButton("Save", DialogInterface.OnClickListener { _, _ ->
                var badHabitsName = input.text.toString()
                badHabitsName
            })
            .setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, _ -> dialog.cancel() })
            .show()
    }


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