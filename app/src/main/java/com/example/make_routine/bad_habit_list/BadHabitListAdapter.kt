package com.example.make_routine.bad_habit_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.make_routine.R

class BadHabitListAdapter(private val items: List<String>?) :
    RecyclerView.Adapter<BadHabitListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bad_habit_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.badHabit.text = items?.get(position)

    }

    override fun getItemCount(): Int {
        return items!!.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val badHabit: TextView = itemView.findViewById(R.id.tvBadHabit)
    }

}