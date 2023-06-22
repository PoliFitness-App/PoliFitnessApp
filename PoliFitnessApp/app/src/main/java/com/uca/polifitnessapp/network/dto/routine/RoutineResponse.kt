package com.uca.polifitnessapp.network.dto.routine

import com.uca.polifitnessapp.data.db.models.routine.RoutineModel

data class RoutineResponse(
    val routines: List<RoutineModel>,
    val totalPages: Int,
    val currentPage: Int
)
