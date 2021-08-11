package com.example.make_routine.ui.home

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.make_routine.BadHabitsApplication
import com.example.make_routine.CreateBadHabitActivity
import com.example.make_routine.adapter.BadHabitAdapter
import com.example.make_routine.dao.BadHabitViewModel
import com.example.make_routine.dao.BadHabitViewModelFactory
import com.example.make_routine.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null

    private val adapter = BadHabitAdapter()
    private var todayDate: String? = null

    companion object {
        fun newInstance() = HomeFragment()
    }

    // 원래 있던 부분
    private lateinit var viewModel: HomeViewModel

    private val badHabitViewModel: BadHabitViewModel by viewModels {
        (activity?.applicationContext as BadHabitsApplication).repo?.let { BadHabitViewModelFactory(it) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Use the ViewModel
        binding.badHabitListRecyclerView.adapter = adapter
        binding.badHabitListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        badHabitViewModel.badHabitNames.observe(requireActivity()) { badHabitName ->
            badHabitName.let { adapter.replaceTo(it) }
        }


        // todayDateTextView 오늘 날짜로 설정하기
        val selectedDateCalendar =
            Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        var selectedDateString = dateFormat.format(selectedDateCalendar.time)
        binding.todayDateTv.text = selectedDateString
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
                binding.todayDateTv.text = selectedDateString

                // 날짜 변경 알림창
                Toast.makeText(requireContext(), "날짜: $selectedDateString", Toast.LENGTH_SHORT)
                    .show()

                // 습관 리스트 업데이트
                // updateHabitListByDate
            }

        // 달력 다이얼로그
        val datePicker = DatePickerDialog(
            requireContext(),
            dateSetListener,
            selectedDateCalendar.get(Calendar.YEAR),
            selectedDateCalendar.get(Calendar.MONTH),
            selectedDateCalendar.get(Calendar.DAY_OF_MONTH)
        )

        // 달력 아이콘 버튼 클릭 시 달력 다이어로그를 띄움.
        binding.calendarBtn.setOnClickListener {
            datePicker.show()
        }

        binding.addRoutineFab.setOnClickListener {
            val createBadHabitIntent = Intent(requireContext(), CreateBadHabitActivity::class.java)
            startActivity(createBadHabitIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}