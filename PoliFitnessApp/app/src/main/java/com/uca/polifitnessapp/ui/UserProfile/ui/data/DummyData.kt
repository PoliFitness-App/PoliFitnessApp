package com.uca.polifitnessapp.ui.UserProfile.ui.data

import androidx.compose.runtime.mutableStateOf

data class User(
    val name: String,
    val height: Double,
    val weight: Double,
    val age: Int,
    val imc: Double,
    val icc: Double,
)

var userTest = mutableStateOf(

    User(
        name = "PoliFitness test",
        height = 1.52,
        weight = 50.0,
        age = 22,
        imc = 21.5,
        icc = 22.9

    )

)



