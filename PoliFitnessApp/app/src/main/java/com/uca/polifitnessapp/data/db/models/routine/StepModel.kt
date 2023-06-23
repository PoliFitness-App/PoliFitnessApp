package com.uca.polifitnessapp.data.db.models.routine

import androidx.room.ColumnInfo

data class StepModel(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String
)
