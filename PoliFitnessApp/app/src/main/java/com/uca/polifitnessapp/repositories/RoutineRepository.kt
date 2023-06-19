package com.uca.polifitnessapp.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.data.db.dao.RoutineDao
import com.uca.polifitnessapp.data.db.models.RoutineModel
import com.uca.polifitnessapp.network.pagination.NewsMediator
import com.uca.polifitnessapp.network.pagination.RoutinesMediator
import com.uca.polifitnessapp.network.service.RoutineService

class RoutineRepository(private val database: PoliFitnessDatabase, private val service: RoutineService) {
    // Instancia del dao
    private val routineDao = database.routineDao()

    // funciones obtenidas del dao
    suspend fun getAllRoutines() = routineDao.getAllRoutines()

    suspend fun insertRoutine(routine: RoutineModel) = routineDao.insertRoutine(routine)

    suspend fun getRoutinesByCategory(category: String) = routineDao.getRoutinesByCategory(category)

    suspend fun getRoutineById(routineId: Int) = routineDao.getRoutineById(routineId)

    suspend fun deleteRoutine(routine: RoutineModel) = routineDao.deleteRoutine(routine)

    suspend fun getRoutinesByApproach(approach: String) = routineDao.getRoutinesByApproach(approach)

    suspend fun getRoutinesByLevel(level: String) = routineDao.getRoutinesByLevel(level)

    suspend fun getRoutinesByLevelAndCategory(level: String, category: String) = routineDao.getRoutinesByLevelAndCategory(level, category)


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
        println("ANTES DE LLAMAR EL RUTINEDAO")
        routineDao.pagingSourceByLevel(query)
    }.flow

    // insertar pager para obtener rutinas por nivel, enfoque y categoria
    @ExperimentalPagingApi
    fun getRoutinesPageByApproachAndCategoryAndLevel(pageSize: Int, approach: String, category: String, level: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        // Concateno ambas querys para guardarlo en claves remotas

        remoteMediator = RoutinesMediator(database, service,"$approach $category $level")
    ) {
        // recibe la query que se le manda desde la vista
        println("ANTES DE LLAMAR EL RUTINEDAO")
        println("$approach $category $level")
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
        println("ANTES DEL DAO")
        routineDao.getAllRoutinesPaging()
    }.flow
}