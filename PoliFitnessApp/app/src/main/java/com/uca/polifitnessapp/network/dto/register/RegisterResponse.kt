package com.uca.polifitnessapp.network.dto.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("msg") val msg: String,
)