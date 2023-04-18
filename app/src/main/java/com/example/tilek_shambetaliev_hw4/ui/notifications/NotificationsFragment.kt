package com.example.tilek_shambetaliev_hw4.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tilek_shambetaliev_hw4.databinding.FragmentNotificationsBinding
import com.example.tilek_shambetaliev_hw4.model.Car
import com.example.tilek_shambetaliev_hw4.ui.notifications.adapter.CarAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var db: FirebaseFirestore
    private val adapter=CarAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root:View=binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        binding.rvCar.adapter=adapter
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
            .addOnSuccessListener{
                val list = it.toObjects(Car::class.java)
                adapter.addCars(list)
            }
            .addOnFailureListener {
                Log.e( "ololo", "onViewCreated: "+it.message)
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
