package com.uca.polifitnessapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.uca.polifitnessapp.data.db.models.RoutineModel

@Dao
interface RoutineDao {

    //Funcion para obtener todas las rutinas
    @Query("SELECT * FROM routine_table")
    suspend fun getAllRoutines(): List<RoutineModel>


    //Funcion para ingresar una rutina
    @Transaction
    @Insert
    suspend fun insertRoutine(routine: RoutineModel)

    //Funcion para obtener una rutina por su categoria
    @Query("SELECT * FROM routine_table WHERE category = :category")
    suspend fun getRoutinesByCategory(category: String): List<RoutineModel>

    //Funcion para obtener una rutina por su id
    @Query("SELECT * FROM routine_table WHERE routineId = :routineId")
    suspend fun getRoutineById(routineId: Int): RoutineModel?

    //funcion para eliminar rutina por id
    @Transaction
    @Delete
    suspend fun deleteRoutine(routine: RoutineModel)

    //funcion para obtener rutinas por enfoque
    @Query("SELECT * FROM routine_table WHERE approach = :approach")
    suspend fun getRoutinesByApproach(approach: String): List<RoutineModel>

    //funcion para obtener rutinas por nivel
    @Query("SELECT * FROM routine_table WHERE level = :level")
    suspend fun getRoutinesByLevel(level: String): List<RoutineModel>

    //funcion para obtener rutinas por nivel y categoria
    @Query("SELECT * FROM routine_table WHERE level = :level AND category = :category")
    suspend fun getRoutinesByLevelAndCategory(level: String, category: String): List<RoutineModel>

}