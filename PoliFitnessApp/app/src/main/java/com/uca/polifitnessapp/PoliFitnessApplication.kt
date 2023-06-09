package com.uca.polifitnessapp

import android.app.Application
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.repositories.RoutineRepository
import com.uca.polifitnessapp.repositories.UserRepository

class PoliFitnessApplication: Application(){
    //implementando la base de datos
    private val database: PoliFitnessDatabase by lazy {
        PoliFitnessDatabase.newInstance(this)
    }


    //implementacion de los repositorios de la aplicacion
    val noticeRepository: NoticeRepository by lazy {
        NoticeRepository(database.noticeDao())
    }

    val routineRepository: RoutineRepository by lazy {
        RoutineRepository(database.routineDao())
    }

    val userRepository: UserRepository by lazy {
        UserRepository(database.userDao())
    }

}