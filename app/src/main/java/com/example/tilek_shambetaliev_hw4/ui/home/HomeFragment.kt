package com.example.tilek_shambetaliev_hw4.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tilek_shambetaliev_hw4.App
import com.example.tilek_shambetaliev_hw4.R
import com.example.tilek_shambetaliev_hw4.databinding.FragmentHomeBinding
import com.example.tilek_shambetaliev_hw4.model.Task
import com.example.tilek_shambetaliev_hw4.ui.task.adapter.TaskAdapter
import com.example.tilek_shambetaliev_hw4.ui.task.TaskFragment.Companion.TASK_KEY
import com.example.tilek_shambetaliev_hw4.ui.task.TaskFragment.Companion.TASK_REQUEST

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!
    private val adapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date= App.db.taskDao().getAll()
        adapter.addTasks(date)

        binding.rvOne.adapter = adapter
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun deleteDB (){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удалить")
            .setMessage("Вы уверены что хотите удалить?")
            .setPositiveButton("Да") { dialog, which ->
                // Обработка нажатия кнопки "Да"
            }
            .setNegativeButton("Нет") { dialog, which ->
                // Обработка нажатия кнопки "Нет"
            }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}