package com.example.make_routine.bad_habit_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.make_routine.R

// BadHabits 리사이클러뷰와 BadHabits 데이터 클래스를 연결해주는 Adapter 클래스 정의.
class BadHabitsAdapter(private val context: Context, private val badHabits: List<BadHabits>) :
    RecyclerView.Adapter<BadHabitsAdapter.ViewHolder>() {

    // 레이아웃 불러오기.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bad_habit, parent, false)
        return ViewHolder(view)
    }

    // 바인드 시작할 때 수행할 명령들.
    // 여기서는 inner class를 이용해서 BadHabit 리스트의 항목들을 각각의 뷰에 할당한다.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(badHabits[position])
    }

    override fun getItemCount(): Int {
        return badHabits.size
    }

    //
    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val badHabitName = itemView?.findViewById<TextView>(R.id.badHabitName)
        private val upCountButton = itemView?.findViewById<ImageButton>(R.id.upCountButton)
        private val downCountButton = itemView?.findViewById<ImageButton>(R.id.downCountButton)

        fun bind(badHabits: BadHabits) {
            badHabitName?.text = badHabits.badHabitName
        }
    }

}