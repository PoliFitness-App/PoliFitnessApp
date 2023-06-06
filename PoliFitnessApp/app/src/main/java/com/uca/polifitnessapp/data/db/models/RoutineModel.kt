package com.uca.polifitnessapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_table")
data class RoutineModel (
    @PrimaryKey(autoGenerate = true) val routineId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "approach") val approach: String,
    @ColumnInfo(name = "level") val level: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "url") val url: String
    ){
    constructor(name: String, description: String, approach: String, level: String, category: String, url: String) :
            this(0, name, description, approach, level, category, url)
}