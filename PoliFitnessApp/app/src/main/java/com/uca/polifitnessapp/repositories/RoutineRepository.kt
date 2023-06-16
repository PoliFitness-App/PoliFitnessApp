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


    // Insertar pager para obtener las rutinas
    @ExperimentalPagingApi
    fun getNewsPage(pageSize: Int, query: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = RoutinesMediator(database, service, query)
    ) {
        // recibe la query que se le manda desde la vista
        routineDao.pagingSource(query)
    }.flow
}