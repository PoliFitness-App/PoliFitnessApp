package com.uca.polifitnessapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserModel(

    @PrimaryKey(autoGenerate = true) var userId: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "height") val height: Float,
    @ColumnInfo(name = "weight") val weight: Float,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "imc") val imc: Float,
    @ColumnInfo(name = "icc") val icc: Float,




    )

{
    constructor(name: String, email: String, height: Float, weight: Float, age:Int, imc: Float, icc: Float):
            this(0, name, email, height, weight, age, imc, icc)
}

