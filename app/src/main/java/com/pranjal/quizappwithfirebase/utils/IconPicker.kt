package com.pranjal.quizappwithfirebase.utils

import com.pranjal.quizappwithfirebase.R

object IconPicker {

    val icon = arrayOf(
            R.drawable.logoquiz,
            R.drawable.logoquiz,
            R.drawable.logoquiz,
            R.drawable.logoquiz,
            R.drawable.logoquiz,
            R.drawable.logoquiz,
            R.drawable.logoquiz,
            R.drawable.logoquiz
            )

    var currentIcon = 0

    fun getIcon() : Int {

        currentIcon= (currentIcon +1 ) % icon.size

        return icon[currentIcon]
    }
}