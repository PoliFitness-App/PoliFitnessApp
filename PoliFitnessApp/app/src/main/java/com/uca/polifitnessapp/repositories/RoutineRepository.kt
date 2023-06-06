package com.uca.polifitnessapp.repositories

import com.uca.polifitnessapp.data.db.dao.RoutineDao
import com.uca.polifitnessapp.data.db.models.RoutineModel

class RoutineRepository(private val routineDao: RoutineDao) {
    //funciones obtenidas del dao
    suspend fun getAllRoutines() = routineDao.getAllRoutines()

    suspend fun insertRoutine(routine: RoutineModel) = routineDao.insertRoutine(routine)

    suspend fun getRoutinesByCategory(category: String) = routineDao.getRoutinesByCategory(category)

    suspend fun getRoutineById(routineId: Int) = routineDao.getRoutineById(routineId)

    suspend fun deleteRoutine(routine: RoutineModel) = routineDao.deleteRoutine(routine)

    suspend fun getRoutinesByApproach(approach: String) = routineDao.getRoutinesByApproach(approach)

    suspend fun getRoutinesByLevel(level: String) = routineDao.getRoutinesByLevel(level)

    suspend fun getRoutinesByLevelAndCategory(level: String, category: String) = routineDao.getRoutinesByLevelAndCategory(level, category)

    //TODO: agregar a application la creacion del repositorio q recibe el dao, cuando se cree la database
}