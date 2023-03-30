package com.example.tilek_shambetaliev_hw4.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(url:String?){
    Glide.with(this).load(url).into(this)
}