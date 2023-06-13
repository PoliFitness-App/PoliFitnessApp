package com.uca.polifitnessapp.network.dto.whoami

import com.google.gson.annotations.SerializedName

data class WhoamiReponse(

    @SerializedName("_id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("roles") val roles: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("imc") val imc: String,
    @SerializedName("icc") val icc: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("height") val height: String,
    @SerializedName("waistP") val waistP: String,
    @SerializedName("hipP") val hipP: String,

)