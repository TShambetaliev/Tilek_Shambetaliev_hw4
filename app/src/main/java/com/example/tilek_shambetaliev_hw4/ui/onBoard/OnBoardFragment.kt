package com.example.tilek_shambetaliev_hw4.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tilek_shambetaliev_hw4.R
import com.example.tilek_shambetaliev_hw4.data.local.Pref
import com.example.tilek_shambetaliev_hw4.databinding.FragmentOnBoardBinding
import com.example.tilek_shambetaliev_hw4.ui.onBoard.adapter.OnBoardingAdapter


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = OnBoardingAdapter(this::onStartClick, this::onNext, )
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                binding.indicator.isVisible=position!=2
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })

    }

    private fun onStartClick() {
        pref.saveSeen()
        findNavController().navigateUp()
    }

    private fun onNext() {
        binding.viewPager.currentItem += 1
    }
}