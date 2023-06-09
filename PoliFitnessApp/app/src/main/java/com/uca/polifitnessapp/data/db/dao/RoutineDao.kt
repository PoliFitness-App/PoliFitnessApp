package com.uca.polifitnessapp.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.uca.polifitnessapp.data.db.models.routine.RoutineModel

@Dao
interface RoutineDao {

    /*
    * Get all routines
    */
    @Query("SELECT * FROM routine_table")
    suspend fun getAllRoutines(): List<RoutineModel>

    /*
    * Get routines for homes screen
    *
    * param count: number of routines to get
     */
    @Query("SELECT * FROM routine_table LIMIT :count")
    suspend fun getRoutines(count: Int): List<RoutineModel>

    // funcion para  obtener rutinas general
    @Query("SELECT * FROM routine_table")
    fun getAllRoutinesPaging(): PagingSource<Int, RoutineModel>

    // funcion para obtener todas las noticias por bloques
    @Query("SELECT * FROM routine_table WHERE category like :query")
    fun pagingSource(query: String): PagingSource<Int, RoutineModel>

    // funcion para buscar por enfoque por bloques
    @Query("SELECT * FROM routine_table WHERE approach like :query")
    fun pagingSourceByApproach(query: String): PagingSource<Int, RoutineModel>

    // funcion para buscar por nivel por bloques
    @Query("SELECT * FROM routine_table WHERE level like :query")
    fun pagingSourceByLevel(query: String): PagingSource<Int, RoutineModel>


    // funcion para buscar por enfoque y categoria por bloques
    @Query("SELECT * FROM routine_table WHERE approach like :query AND category like :query2")
    fun pagingSourceByApproachAndCategory(
        query: String,
        query2: String
    ): PagingSource<Int, RoutineModel>

    // funcion para buscar por enfoque y nivel por bloques
    @Query("SELECT * FROM routine_table WHERE approach like :query AND level like :query2")
    fun pagingSourceByApproachAndLevel(
        query: String,
        query2: String
    ): PagingSource<Int, RoutineModel>

    // funcion para buscar por enfoque, categoria y nivel por bloques
    @Query("SELECT * FROM routine_table WHERE approach like :query AND category like :query2 AND level like :query3")
    fun pagingSourceByApproachAndCategoryAndLevel(
        query: String,
        query2: String,
        query3: String
    ): PagingSource<Int, RoutineModel>


    //Funcion para ingresar una rutina
    @Transaction
    @Insert
    suspend fun insertRoutine(routine: RoutineModel)

    //Funcion para obtener una rutina por su categoria
    @Query("SELECT * FROM routine_table WHERE category = :category")
    suspend fun getRoutinesByCategory(category: String): List<RoutineModel>

    //Funcion para obtener una rutina por su id
    @Query("SELECT * FROM routine_table WHERE routineId = :routineId")
    suspend fun getRoutineById(routineId: String): RoutineModel?

    //funcion para eliminar rutina por id
    @Transaction
    @Delete
    suspend fun deleteRoutine(routine: RoutineModel)
    //funcion toggle para ocultar rutina por id

    /*
    * //funcion toggle para ocultar rutina por id
    @Query("UPDATE routine_table SET hidden = :hidden WHERE routineId = :routineId")
    suspend fun toggleRoutine(routineId: Int, hidden: Boolean)
    * */

    //funcion para obtener rutinas por enfoque
    @Query("SELECT * FROM routine_table WHERE approach = :approach")
    suspend fun getRoutinesByApproach(approach: String): List<RoutineModel>

    //funcion para obtener rutinas por nivel
    @Query("SELECT * FROM routine_table WHERE level = :level")
    suspend fun getRoutinesByLevel(level: String): List<RoutineModel>

    //funcion para obtener rutinas por nivel y categoria
    @Query("SELECT * FROM routine_table WHERE level = :level AND category = :category")
    suspend fun getRoutinesByLevelAndCategory(level: String, category: String): List<RoutineModel>

    // funcion para el pagination limpiar base
    @Query("DELETE FROM routine_table")
    suspend fun clearAll()

    // funcion para el pagination insertar lista
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(routines: List<RoutineModel>)

}