package com.example.tilek_shambetaliev_hw4.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.tilek_shambetaliev_hw4.App
import com.example.tilek_shambetaliev_hw4.R
import com.example.tilek_shambetaliev_hw4.databinding.FragmentTaskBinding
import com.example.tilek_shambetaliev_hw4.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.isNotEmpty()) {
                save()
            } else binding.etTitle.error = "Это поле обязательна для заполнения"
            findNavController().navigateUp()
        }
    }

    private fun save() {
        val data =
            Task(title = binding.etTitle.text.toString(), desc = binding.etDesc.text.toString())
        App.db.taskDao().insert(data)
    }

    companion object {
        const val TASK_REQUEST = "task"
        const val TASK_KEY = "key"
    }
}