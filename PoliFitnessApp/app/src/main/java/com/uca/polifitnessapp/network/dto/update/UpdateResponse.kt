package com.uca.polifitnessapp.network.dto.update

import com.google.gson.annotations.SerializedName

data class UpdateResponse (
    @SerializedName("_id") val _id: String,
    @SerializedName("weight") val weight: Float,
    @SerializedName("height") val height: Float,
    @SerializedName("waistP") val waistP: Float,
    @SerializedName("hipP") val hipP: Float,
    @SerializedName("approach") val approach: String,
    @SerializedName("icc") val icc: Float,
    @SerializedName("imc") val imc: Float,
    @SerializedName("message") val message: String,
)