package com.example.tilek_shambetaliev_hw4.ui.onBoard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tilek_shambetaliev_hw4.databinding.ItemOnboardBinding
import com.example.tilek_shambetaliev_hw4.model.OnBoard
import com.example.tilek_shambetaliev_hw4.utils.loadImage

class OnBoardingAdapter(
    private val onClick: () -> Unit,
    private val onNext: () -> Unit
) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val data = arrayListOf<OnBoard>(
        OnBoard(
            "https://toggl.com/blog/wp-content/uploads/2018/09/project-task-list.jpg",
            "Task Manager 1",
            "Task Manager Description"
        ),
        OnBoard(
            "https://flow-e.com/wp-content/uploads/bfi_thumb/Google-task-list-379tmv50jkyo35v5zqpoui.png",
            "Task Manager 2",
            "Task Manager Description"
        ),
        OnBoard(
            "https://img.officetimeline.com/website/Content/images/articles/PM-Task-Management/task-management-hero-banner.png",
            "Task Manager 3",
            "Task Manager Description"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class OnBoardingViewHolder(private val binding: ItemOnboardBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.description
            binding.imgBoard.loadImage(onBoard.image)
            binding.tvSkip.isVisible = adapterPosition != 2
            binding.btnStart.isVisible = adapterPosition == 2
            binding.tvSkip.setOnClickListener {
                onClick()
            }
            binding.btnStart.setOnClickListener {
                onClick()
            }
            binding.tvNextup.setOnClickListener {
                onNext()
            }
            binding.tvNextup.isVisible = adapterPosition != 2
        }
    }
}