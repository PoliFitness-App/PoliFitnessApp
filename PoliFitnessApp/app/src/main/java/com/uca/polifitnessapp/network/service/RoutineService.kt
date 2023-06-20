package com.uca.polifitnessapp.network.service

import com.uca.polifitnessapp.network.dto.routine.RoutineResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface RoutineService {
    // Obtener rutinas por bloques (todas)
    @GET("api/rutine")
    suspend fun getRoutinesByBlocks(@Query("page") page: Int, @Query("limit") limit: Int): RoutineResponse

    // Obtener rutinas por categoria
    @GET("api/rutine/getRoutineByCategory")
    suspend fun getRoutineByCategory(@Query("category") category: String): List<RoutineResponse>

    // Eliminar rutina por id
    @PATCH("api/rutine/deleteRoutine/{id}")
    suspend fun hideRoutineById(@Query("id") id: String): RoutineResponse

    // Obtener rutina por enfoque
    @GET("api/rutine/getRoutineByAproach")
    suspend fun getRoutineByAproach(@Query("aproach") aproach: String): List<RoutineResponse>

    // Obtener rutina por nivel
    @GET("api/rutine/getRoutineByLevel")
    suspend fun getRoutineByLevel(@Query("level") level: String): List<RoutineResponse>

    // Obtener rutina por nivel y enfoque
    @GET("api/rutine/getRoutineByLevelAndAproach")
    suspend fun getRoutineByLevelAndAproach(@Query("level") level: String, @Query("aproach") aproach: String): List<RoutineResponse>

}