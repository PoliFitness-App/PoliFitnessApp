package com.uca.polifitnessapp.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.uca.polifitnessapp.data.PoliFitnessDatabase
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.data.db.models.routine.RoutineModel
import com.uca.polifitnessapp.network.ApiResponse
import com.uca.polifitnessapp.network.dto.update.UpdateRequest
import com.uca.polifitnessapp.network.dto.update.UpdateResponse
import com.uca.polifitnessapp.network.pagination.RoutinesMediator
import com.uca.polifitnessapp.network.service.RoutineService
import retrofit2.HttpException
import java.io.IOException

class RoutineRepository(
    private val database: PoliFitnessDatabase,
    private val service: RoutineService
    ) {
    // Instancia del dao
    private val routineDao = database.routineDao()

    // funciones obtenidas del dao
    suspend fun getAllRoutines() = routineDao.getAllRoutines()

    /*
    * Get routines for home screen
     */
    suspend fun getRoutines(count:Int): List<RoutineModel> {
        try {
            val response = service.getRoutinesByBlocks(
                page = 1 , limit = 3
            )
            return response.routines
        } catch (e: HttpException) {
            if (e.code() == 400) {
                return emptyList()
            }
        } catch (_: IOException) {
        }
        return routineDao.getRoutines(count)
    }

    suspend fun getRoutineById(routineId: String) = routineDao.getRoutineById(routineId)

    // Insertar pager para obtener las rutinas, categoria
    @ExperimentalPagingApi
    fun getRoutinesPage(pageSize: Int, query: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = RoutinesMediator(database, service, query)
    ) {
        // recibe la query que se le manda desde la vista
        routineDao.pagingSource(query)
    }.flow

    // Insertar pager para obtener las rutinas, por enfoque
    @ExperimentalPagingApi
    fun getRoutinesPageByAproach(pageSize: Int, query: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = RoutinesMediator(database, service, query)
    ) {
        // recibe la query que se le manda desde la vista
        routineDao.pagingSourceByApproach(query)
    }.flow

    // Insertar pager para obtener las rutinas, por enfoque y categoria
    @ExperimentalPagingApi
    fun getRoutinesPageByAproachAndCategory(pageSize: Int, query: String, query2: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        // Concateno ambas querys para guardarlo en claves remotas
        remoteMediator = RoutinesMediator(database, service, query + query2)
    ) {
        // recibe la query que se le manda desde la vista
        routineDao.pagingSourceByApproachAndCategory(query, query2) // query2 es la categoria
    }.flow

    // Insertar pager para obtener las rutinas, por enfoque y nivel
    @ExperimentalPagingApi
    fun getRoutinesPageByAproachAndLevel(pageSize: Int, query: String, query2: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        // Concateno ambas querys para guardarlo en claves remotas
        remoteMediator = RoutinesMediator(database, service, query + query2)
    ) {
        // recibe la query que se le manda desde la vista
        routineDao.pagingSourceByApproachAndLevel(query, query2) // query2 es el nivel
    }.flow

    // Insertar pager para obtener las rutinas, por nivel
    @ExperimentalPagingApi
    fun getRoutinesPageByLevel(pageSize: Int, query: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        // Concateno ambas querys para guardarlo en claves remotas
        remoteMediator = RoutinesMediator(database, service, query)
    ) {
        // recibe la query que se le manda desde la vista
        routineDao.pagingSourceByLevel(query)
    }.flow

    // insertar pager para obtener rutinas por nivel, enfoque y categoria
    @ExperimentalPagingApi
    fun getRoutinesPageByApproachAndCategoryAndLevel(pageSize: Int, approach: String, category: String, level: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize
        ),
        // Concateno ambas querys para guardarlo en claves remotas

        remoteMediator = RoutinesMediator(database, service,"$approach $category $level")
    ) {
        // recibe la query que se le manda desde la vista
        routineDao.pagingSourceByApproachAndCategoryAndLevel(approach, category, level)
    }.flow

    // Insertar pager para obtener las rutinas, todas
    @ExperimentalPagingApi
    fun getAllRoutinesPage(pageSize: Int, query: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize
        ),
        remoteMediator = RoutinesMediator(database, service, query)
    ) {
        // recibe la query que se le manda desde la vista
        routineDao.getAllRoutinesPaging()
    }.flow
}