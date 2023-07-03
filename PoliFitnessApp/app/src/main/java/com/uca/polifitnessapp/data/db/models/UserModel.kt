package com.uca.polifitnessapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_table")
data class UserModel(
    @PrimaryKey @SerializedName("_id") val _id: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "lastname") var lastname: String,
    @ColumnInfo(name = "imc") var imc: Float,
    @ColumnInfo(name = "icc") var icc: Float,
    @ColumnInfo(name = "gender") var gender: Boolean,
    @ColumnInfo(name = "birthday") var birthday: String,
    @ColumnInfo(name = "height") var height: Float,
    @ColumnInfo(name = "weight") var weight: Float,
    @ColumnInfo(name = "waistP") var waistP: Float,
    @ColumnInfo(name = "hipP") var hipP: Float,
    @ColumnInfo(name = "approach") var approach: String
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

