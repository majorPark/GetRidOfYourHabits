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

class BadHabitsAdapter(private val context: Context, private val badHabits: List<BadHabits>) : RecyclerView.Adapter<BadHabitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bad_habit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bind(badHabits[position])

    }

    override fun getItemCount(): Int {
        return badHabits!!.size

    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val badHabitName = itemView?.findViewById<TextView>(R.id.badHabitName)
        val upCountButton = itemView?.findViewById<ImageButton>(R.id.upCountButton)
        val downCountButton = itemView?.findViewById<ImageButton>(R.id.downCountButton)

        fun bind(badHabits: BadHabits) {
            badHabitName?.text = badHabits.badHabitName
        }
    }

}