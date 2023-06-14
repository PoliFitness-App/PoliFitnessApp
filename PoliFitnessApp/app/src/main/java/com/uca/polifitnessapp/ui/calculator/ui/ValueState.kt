package com.uca.polifitnessapp.ui.calculator.ui

data class ValueState(
    val value: String = "", // Input del usuario
    val error: String? = null // error si es null
) {
    fun toNumber() = value.toDoubleOrNull()
}

