package com.uca.polifitnessapp.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uca.polifitnessapp.data.db.dao.NoticeDao
import com.uca.polifitnessapp.data.db.dao.RemoteKeyDao
import com.uca.polifitnessapp.data.db.dao.RoutineDao
import com.uca.polifitnessapp.data.db.dao.UserDao
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.data.db.models.RemoteKey
import com.uca.polifitnessapp.data.db.models.RoutineModel
import com.uca.polifitnessapp.data.db.models.UserModel

@Database(entities = [UserModel::class, RoutineModel::class, NoticeModel::class, RemoteKey::class], version = 9, exportSchema = false)
abstract class PoliFitnessDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao
    abstract fun routineDao(): RoutineDao
    abstract fun noticeDao(): NoticeDao

    //funcion de claves remotas
    abstract fun remoteKeysDao(): RemoteKeyDao

    companion object{
        @Volatile
        private var INSTANCE : PoliFitnessDatabase? = null

        fun newInstance(application: Application): PoliFitnessDatabase{
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    PoliFitnessDatabase::class.java,
                    "poliFitness_app"
                ).fallbackToDestructiveMigration().build()

                INSTANCE= instance
                instance

            }

        }
    }
}

