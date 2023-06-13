package com.uca.polifitnessapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey @SerializedName("_id") val _id: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "lastname") val lastname: String,
    @ColumnInfo(name = "imc") val imc: Float,
    @ColumnInfo(name = "icc") val icc: Float,
    @ColumnInfo(name = "gender") val gender: Boolean,
    @ColumnInfo(name = "birthday") val birthday: String,
    @ColumnInfo(name = "height") val height: Float,
    @ColumnInfo(name = "weight") val weight: Float,
    @ColumnInfo(name = "waistP") val waistP: String,
    @ColumnInfo(name = "hipP") val hipP: String,
    ) {
    constructor(_id: String, name: String, email: String, height: Float, weight: Float, imc: Float, icc: Float)
            : this("", "", "", "", 0.0F, 0.0F, false, "", 0.0F, 0.0F, "", "")
}

