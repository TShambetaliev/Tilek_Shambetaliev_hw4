package com.example.tilek_shambetaliev_hw4.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.tilek_shambetaliev_hw4.data.local.Pref
import com.example.tilek_shambetaliev_hw4.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(requireContext())
        loadImage()

        binding.etImgName.setText(pref.loadText())
        binding.imgOne.setImageURI(pref.loadImage()?.toUri())

    }

    private fun loadImage() {
        binding.imgOne.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type="image/*"
            startActivityForResult(intent, IMAGE_KEYS )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_KEYS&& resultCode == RESULT_OK && data != null ){
            val image = data.data
            binding.imgOne.setImageURI(data.data)

            pref.saveImage(image.toString())
        }
    }

    override fun onStop() {
        super.onStop()
        pref.saveText(binding.etImgName.text.toString())
    }

    companion object {
        const val IMAGE_KEYS = 1995
    }
}