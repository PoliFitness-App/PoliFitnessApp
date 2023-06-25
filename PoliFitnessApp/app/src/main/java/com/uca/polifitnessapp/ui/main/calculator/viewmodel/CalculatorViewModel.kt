package com.uca.polifitnessapp.ui.main.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uca.polifitnessapp.ui.main.calculator.ui.ValueState

class CalculatorViewModel: ViewModel() {

    var bmi by mutableStateOf(0.0)
        private set

    var icc by mutableStateOf(0.0)
        private set

    var messageIbm by mutableStateOf("")
        private set

    var messageIcc by mutableStateOf("")
        private set
    var heightState by mutableStateOf(ValueState())
        private set
    var weightState by mutableStateOf(ValueState())
        private set

    var waistState by mutableStateOf(ValueState())
        private set

    var hipState by mutableStateOf(ValueState())
        private set

    var genderState by mutableStateOf(ValueState())

    var weightUnitState by mutableStateOf("KG")

    var isButtonEnabled by mutableStateOf(false)

    fun updateHeight(it: String) {
        heightState = heightState.copy(value = it, error = null)
        checkButton()
    }

    fun updateWeight(it: String) {
        weightState = weightState.copy(value = it, error = null)
        checkButton()
    }

    fun updateWaist(it: String) {
        waistState = waistState.copy(value = it, error = null)
        checkButton()
    }

    fun updateHip(it: String) {
        hipState = hipState.copy(value = it, error = null)
        checkButton()
    }

    fun updateGender(it: String) {
        genderState = genderState.copy(value = it, error = null)
        checkButton()
        //updateImage(it)
    }

    fun checkButton(){
        isButtonEnabled =
            genderState.value.isNotEmpty() &&
                weightState.value.isNotEmpty()&&
                heightState.value.isNotEmpty()&&
                hipState.value.isNotEmpty()&&
                waistState.value.isNotEmpty()
    }

    fun changeUnit() {
        weightUnitState = if (weightUnitState == "KG")
            "LB"
        else
            "KG"
    }


    fun calculate() {
        if (heightState.value.isBlank())
            heightState = heightState.copy(error = "Invalid number")
        else if (weightState.value.isBlank())
            weightState = weightState.copy(error = "Invalid number")
        else if (waistState.value.isBlank())
            waistState = waistState.copy(error = "Invalid number")
        else if (hipState.value.isBlank())
            hipState = hipState.copy(error = "Invalid number")
        else calculateBMI(heightState.toNumber()!!, weightState.toNumber()!!, waistState.toNumber()!!,hipState.toNumber()!! , genderState.value)
    }

    private fun calculateBMI(
        height: Double,
        weight: Double,
        waist: Double,
        hip: Double,
        gender: String
    ) {
        if (gender == "Femenino" && weightUnitState == "KG") {

            bmi = weight / (height * height)
            messageIbm = when {
                bmi < 18.5 -> "Bajo peso"
                bmi in 18.5..24.9 -> "Peso saludable"
                bmi in 25.0..29.9 -> "Sobrepeso"
                bmi >= 30.0 -> "Peligro de sobrepeso"
                else -> error("Invalid params")
            }
            icc = (waist / hip) * 10
            messageIcc = when {
                icc < 0.80 -> "Riesgo Bajo"
                icc in 0.81..0.84 -> "Riesgo Alto"
                icc >= 0.85 -> "Muy alto"
                else -> error("Invalid params")
            }
        } else if (gender == "Femenino" && weightUnitState == "LB") {

            bmi = (weight/2.205) / (height * height)

            messageIbm = when {
                bmi < 18.5 -> "Bajo peso"
                bmi in 18.5..24.9 -> "Peso saludable"
                bmi in 25.0..29.9 -> "Sobrepeso"
                bmi >= 30.0 -> "Peligro de sobrepeso"
                else -> error("Invalid params")
            }

            icc = (waist / hip) * 10
            messageIcc = when {
                icc < 0.80 -> "Riesgo Bajo"
                icc in 0.81..0.84 -> "Riesgo Alto"
                icc >= 0.85 -> "Muy alto"
                else -> error("Invalid params")
            }


        } else if(gender == "Masculino" && weightUnitState == "KG"){
            bmi = weight / (height * height)

            messageIbm = when {
                bmi < 18.5 -> "Bajo peso"
                bmi in 18.5..24.9 -> "Peso saludable"
                bmi in 25.0..29.9 -> "Sobrepeso"
                bmi >= 30.0 -> "Peligro de sobrepeso"
                else -> error("Invalid params")
            }

            icc = (waist / hip) * 10
            messageIcc = when {
                icc < 0.95 -> "Riesgo Bajo"
                icc in 0.96..0.99 -> "Riesgo Alto"
                icc >= 1.0 -> "Muy alto"
                else -> error("Invalid params")
            }
        }
        else{
            bmi = (weight/2.205) / (height * height)

            messageIbm = when {
                bmi < 18.5 -> "Bajo peso"
                bmi in 18.5..24.9 -> "Peso saludable"
                bmi in 25.0..29.9 -> "Sobrepeso"
                bmi >= 30.0 -> "Peligro de sobrepeso"
                else -> error("Invalid params")
            }

            icc = (waist / hip) * 10
            messageIcc = when {
                icc < 0.95 -> "Riesgo Bajo"
                icc in 0.96..0.99 -> "Riesgo Alto"
                icc >= 1.0 -> "Muy alto"
                else -> error("Invalid params")
            }
        }
        clearData()
    }

    fun clearData(){
        genderState = genderState.copy(value = "", error = null)
        heightState = heightState.copy(value = "", error = null)
        weightState = weightState.copy(value = "", error = null)
        waistState = waistState.copy(value = "", error = null)
        hipState = hipState.copy(value = "", error = null)
        isButtonEnabled = false
    }
}