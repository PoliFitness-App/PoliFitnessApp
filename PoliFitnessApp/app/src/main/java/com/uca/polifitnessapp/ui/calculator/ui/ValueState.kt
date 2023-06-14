package com.uca.polifitnessapp.ui.calculator.ui

data class ValueState(
    var value: String = "", // Input del usuario
    val error: String? = null // error si es null
) {
    fun toNumber() = value.toDoubleOrNull()
}

