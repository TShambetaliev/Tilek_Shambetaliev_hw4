package com.example.tilek_shambetaliev_hw4.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tilek_shambetaliev_hw4.App
import com.example.tilek_shambetaliev_hw4.R
import com.example.tilek_shambetaliev_hw4.databinding.FragmentHomeBinding
import com.example.tilek_shambetaliev_hw4.model.Task
import com.example.tilek_shambetaliev_hw4.ui.task.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this::deleteDB)

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

        newDB()

        binding.rvOne.adapter = adapter
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

    }

    private fun newDB() {
        val date = App.db.taskDao().getAll()
        adapter.addTasks(date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteDB(task: Task) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удалить")
            .setMessage("Вы уверены что хотите удалить?")
            .setPositiveButton("Да") { dialog, which ->
                App.db.taskDao().delete(task)
                newDB()
            }
            .setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}