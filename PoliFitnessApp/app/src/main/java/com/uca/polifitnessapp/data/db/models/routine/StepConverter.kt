package com.uca.polifitnessapp.data.db.models.routine

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StepConverter {
    @TypeConverter
    fun fromString(value: String): List<StepModel> {
        val listType = object : TypeToken<List<StepModel>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(steps: List<StepModel>): String {
        return Gson().toJson(steps)
    }
}