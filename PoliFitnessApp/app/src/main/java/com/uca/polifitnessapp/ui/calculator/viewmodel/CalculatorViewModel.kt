package com.uca.polifitnessapp.ui.calculator.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.Male
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
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

    //Gender change icon selection

  /*  var iconGender  by mutableStateOf(Icons.Outlined.Male)
    private set

    fun setIconGender(icon: ImageVector){
        iconGender = icon
    }
    */
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

    fun updateGender(it: String) {
        genderState = genderState.copy(value = it, error = null)
    }


    fun changeUnit() {
        if (weightUnitState == "KG")
            weightUnitState = "LB"
        else
            weightUnitState = "KG"
    }


    fun calculate() {
        val height = heightState.toNumber()
        val weight = weightState.toNumber()
        val waist = heightState.toNumber()
        val hip = weightState.toNumber()
        val gender = genderState.value

        if (height == null)
            heightState = heightState.copy(error = "Invalid number")
        else if (weight == null)
            weightState = weightState.copy(error = "Invalid number")
        else if (waist == null)
            waistState = waistState.copy(error = "Invalid number")
        else if (hip == null)
            hipState = hipState.copy(error = "Invalid number")
        else calculateBMI(height, weight, waist, hip, gender)
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
                bmi < 21 -> "Bajo"
                bmi in 21.0..32.9 -> "Normal"
                bmi in 33.0..38.9 -> "Alto"
                bmi >= 39.0 -> "Muy Alto"
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
                bmi < 21 -> "Bajo"
                bmi in 21.0..32.9 -> "Normal"
                bmi in 33.0..38.9 -> "Alto"
                bmi >= 39.0 -> "Muy Alto"
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
                bmi < 8.0 -> "Bajo"
                bmi in 8.0..19.9 -> "Normal"
                bmi in 20.0..24.9 -> "Alto"
                bmi >= 25.0 -> "Muy Alto"
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
                bmi < 8.0 -> "Bajo"
                bmi in 8.0..19.9 -> "Normal"
                bmi in 20.0..24.9 -> "Alto"
                bmi >= 25.0 -> "Muy Alto"
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

    }
}