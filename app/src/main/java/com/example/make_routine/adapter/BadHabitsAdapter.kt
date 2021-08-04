package com.example.make_routine.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.make_routine.R
import com.example.make_routine.model.BadHabit

class BadHabitAdapter: RecyclerView.Adapter<BadHabitAdapter.BadHabitViewHolder>() {
    private val diffUtil = AsyncListDiffer(this, BadHabitDiffUtilCallback())

    fun replaceTo(newItems: List<BadHabit>) = diffUtil.submitList(newItems)
    fun getItem(position: Int) = diffUtil.currentList[position]

    override fun getItemCount(): Int = diffUtil.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadHabitViewHolder {
        return BadHabitViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BadHabitViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    class BadHabitViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val badHabitName = itemView?.findViewById<TextView>(R.id.badHabitName)
        val upCountButton = itemView?.findViewById<ImageButton>(R.id.upCountButton)
        val downCountButton = itemView?.findViewById<ImageButton>(R.id.downCountButton)

        fun bind(badHabit: BadHabit) {
            badHabitName?.text = badHabit.badHabitName
        }

        companion object {
            fun create(parent: ViewGroup): BadHabitViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_bad_habit, parent, false)

                return BadHabitViewHolder(view)
            }
        }
    }
}

class BadHabitDiffUtilCallback: DiffUtil.ItemCallback<BadHabit>() {
    override fun areItemsTheSame(oldItem: BadHabit, newItem: BadHabit): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: BadHabit, newItem: BadHabit): Boolean {
        return oldItem.badHabitName == newItem.badHabitName
    }
}