package com.pranjal.quizappwithfirebase.utils

object ColorPicker {

    val colors = arrayOf("#c7006e", "#f47100", "#3d00e0", "#008b00", "#8b00dd", "#880061", "#009763", "#fc180c")

    var currentColorIndex = 0

    fun getColor() : String {

        currentColorIndex =(currentColorIndex +1 ) % colors.size

        return colors[currentColorIndex]
    }
}