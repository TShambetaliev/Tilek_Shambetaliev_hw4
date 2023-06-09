package com.example.tilek_shambetaliev_hw4.ui.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tilek_shambetaliev_hw4.R
import com.example.tilek_shambetaliev_hw4.databinding.ItemTaskBinding
import com.example.tilek_shambetaliev_hw4.model.Task

class TaskAdapter(private val onClik: (Task) -> Unit) : Adapter<TaskAdapter.TaskViewHolder>() {

    private val data: ArrayList<Task> = arrayListOf()

    fun addTask(task: Task) {
        data.add(0, task)
        notifyItemChanged(0)
    }

    fun addTasks(task: List<Task>) {
        data.clear()
        data.addAll(task)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            if (adapterPosition % 2 == 0) {
                itemView.setBackgroundColor(Color.BLACK)
                binding.tvTitle.setTextColor(Color.WHITE)
                binding.tvDesc.setTextColor(Color.WHITE)
            } else {
                itemView.setBackgroundColor(Color.WHITE)
                binding.tvTitle.setTextColor(Color.BLACK)
                binding.tvDesc.setTextColor(Color.BLACK)
            }
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            itemView.setOnLongClickListener {
                onClik(task)
                false
            }
        }
    }
}