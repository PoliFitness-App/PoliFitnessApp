package com.uca.polifitnessapp.ui.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uca.polifitnessapp.ui.calculator.ui.ValueState

class CalculatorViewModel: ViewModel() {

    var bmi by mutableStateOf(0.0)
        private set

    var icc by mutableStateOf(0.0)
        private set

    var messageIbm by mutableStateOf("")
        private set

    var messageIcc by mutableStateOf("")
        private set
    var heightState by mutableStateOf(ValueState("Height", "m"))
        private set
    var weightState by mutableStateOf(ValueState("Weight", "kg"))
        private set

    var waistState by mutableStateOf(ValueState("Weight", "kg"))
        private set

    var hipState by mutableStateOf(ValueState("Weight", "kg"))
        private set

    fun updateHeight(it: String) {
        heightState = heightState.copy(value = it, error = null)
    }

    fun updateWeight(it: String) {
        weightState = weightState.copy(value = it, error = null)
    }

    fun updateWaist(it: String) {
        waistState = waistState.copy(value = it, error = null)
    }

    fun updateHip(it: String) {
        hipState = hipState.copy(value = it, error = null)
    }

    fun calculate() {
        val height = heightState.toNumber()
        val weight = weightState.toNumber()
        val waist = heightState.toNumber()
        val hip = weightState.toNumber()
        if (height == null)
            heightState = heightState.copy(error = "Invalid number")
        else if (weight == null)
            weightState = weightState.copy(error = "Invalid number")
        else if (waist == null)
            waistState = waistState.copy(error = "Invalid number")
        else if (hip == null)
            hipState = hipState.copy(error = "Invalid number")
        else calculateBMI(height, weight, waist, hip)
    }

    private fun calculateBMI(height: Double, weight: Double, waist: Double, hip: Double) {
        bmi = weight / (height * height)

        messageIbm = when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal"
            bmi in 25.0..29.9 -> "Overweight"
            bmi >= 30.0 -> "Obsess"
            else -> error("Invalid params")
        }

        icc = waist/hip
        messageIcc= when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal"
            bmi in 25.0..29.9 -> "Overweight"
            bmi >= 30.0 -> "Obsess"
            else -> error("Invalid params")
        }

    }



    fun clear() {
        heightState = heightState.copy(value = "", error = null)
        weightState = weightState.copy(value = "", error = null)
        bmi = 0.0
        icc = 0.0
        messageIbm = ""
        messageIcc = ""
    }
}