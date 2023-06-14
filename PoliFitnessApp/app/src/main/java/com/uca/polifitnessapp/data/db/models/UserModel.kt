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
    @ColumnInfo(name = "height") var height: Float,
    @ColumnInfo(name = "weight") var weight: Float,
    @ColumnInfo(name = "waistP") var waistP: Float,
    @ColumnInfo(name = "hipP") var hipP: Float,
    @ColumnInfo(name = "approach") val approach: String
) {
    constructor(
        _id: String,
        name: String,
        email: String,
        height: Float,
        weight: Float,
        imc: Float,
        icc: Float,
        approach: String
    )
            : this(
        "",
        "",
        "",
        "",
        0F,
        0F,
        false,
        "",
        0F,
        0F,
        0F,
        0F,
        ""
    )
}

