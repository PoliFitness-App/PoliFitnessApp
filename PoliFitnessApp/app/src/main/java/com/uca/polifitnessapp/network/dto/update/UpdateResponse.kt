package com.uca.polifitnessapp.network.dto.update

import com.google.gson.annotations.SerializedName

data class UpdateResponse (
    @SerializedName("_id") val _id: String,
    @SerializedName("weight") val weight: Float,
    @SerializedName("height") val height: Float,
    @SerializedName("waistP") val waistP: String,
    @SerializedName("hipP") val hipP: String,
    @SerializedName("message") val message: String,
)