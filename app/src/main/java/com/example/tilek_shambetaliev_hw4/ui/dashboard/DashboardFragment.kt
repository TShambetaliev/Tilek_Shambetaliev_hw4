package com.example.tilek_shambetaliev_hw4.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tilek_shambetaliev_hw4.data.local.Pref
import com.example.tilek_shambetaliev_hw4.databinding.FragmentDashboardBinding
import com.example.tilek_shambetaliev_hw4.model.Car
import com.example.tilek_shambetaliev_hw4.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var pref: Pref
    private lateinit var db: FirebaseFirestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        db = FirebaseFirestore.getInstance()
        binding.btnCarSave.setOnClickListener {
            saveCar()
        }
    }

    private fun saveCar() {
        val name = binding.etCarName.text.toString()
        val model = binding.etCarModel.text.toString()
        val car = Car(name, model)

        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).document().set(car)
            .addOnSuccessListener {
                showToast("Данные сохранены!")
                binding.etCarName.text?.clear()
                binding.etCarModel.text?.clear()
            }
            .addOnFailureListener {

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}