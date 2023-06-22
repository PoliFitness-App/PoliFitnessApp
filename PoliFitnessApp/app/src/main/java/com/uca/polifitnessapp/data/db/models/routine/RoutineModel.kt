package com.uca.polifitnessapp.data.db.models.routine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "routine_table")
data class RoutineModel(
    @PrimaryKey @SerializedName("_id") val routineId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "approach") val approach: String,
    @ColumnInfo(name = "level") val level: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "hidden") val hidden: Boolean,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "steps") val steps:  List<StepModel>
) {
    constructor(
        title: String,
        description: String,
        approach: String,
        level: String,
        category: String,
        user: String,
        hidden: Boolean,
        url: String,
        steps: List<StepModel>
    ) :
            this(
                "",
                title,
                description,
                approach,
                level,
                category,
                user,
                hidden,
                url,
                steps
            )
}