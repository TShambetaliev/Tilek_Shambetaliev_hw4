package com.example.tilek_shambetaliev_hw4.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {
    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    fun isUserSeen(): Boolean {
        return pref.getBoolean(ONBOARD_KEY, false)
    }

    fun saveSeen() {
        pref.edit().putBoolean(ONBOARD_KEY, true).apply()
    }

    fun saveText(text: String) {
        pref.edit().putString(TEXT_KEY, text).apply()
    }

    fun loadText() = pref.getString(TEXT_KEY, "")

    fun saveImage(text: String){
        pref.edit().putString(IMAGE_KEY, text).apply()
    }

    fun loadImage()=pref.getString(IMAGE_KEY, "")

    companion object {
        const val PREF_NAME = "task.name.53"
        const val ONBOARD_KEY = "onBoardKey"
        const val TEXT_KEY = "textKey"
        const val IMAGE_KEY = "imageKey"
    }
}