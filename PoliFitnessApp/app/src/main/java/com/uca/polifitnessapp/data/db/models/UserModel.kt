package com.uca.polifitnessapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserModel(

    @PrimaryKey(autoGenerate = true) var userId: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "age") val age: Int,



    )

{
    constructor(name: String, email: String, height: Int, weight: Int, age:Int ):
            this(0, name, email, height, weight, age)
}

