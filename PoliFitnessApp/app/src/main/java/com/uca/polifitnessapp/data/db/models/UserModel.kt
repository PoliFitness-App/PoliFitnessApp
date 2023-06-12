package com.uca.polifitnessapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserModel(

    @PrimaryKey(autoGenerate = true) var userId: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "height") val height: Double,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "imc") val imc: Double,
    @ColumnInfo(name = "icc") val icc: Double,

    )

{
    constructor(name: String, email: String, height: Double, weight: Double, age:Int, imc: Double, icc: Double):
            this(0, name, email, height, weight, age, imc, icc)
}

