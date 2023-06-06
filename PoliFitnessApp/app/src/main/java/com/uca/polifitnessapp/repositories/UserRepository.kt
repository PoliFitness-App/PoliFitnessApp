package com.uca.polifitnessapp.repositories

import com.uca.polifitnessapp.data.db.dao.UserDao
import com.uca.polifitnessapp.data.db.models.UserModel

class UserRepository(private val userDao: UserDao ) {

    suspend fun getAllUsers() = userDao.getAllUsers()

    suspend fun addUser(user: UserModel) = userDao.insertUser(user)

    suspend fun getUserById(id: Int) = userDao.getUserById(id)

    suspend fun getUserByName( name: String) = userDao.getUserByName(name)

    suspend fun updateUser(user: UserModel) =  userDao.updateUser(user)

    suspend fun deleteUser(user: UserModel) = userDao.deleteUser(user)

    suspend fun updateImcIccUser(id: Int, imc: Float, icc: Float) = userDao.updateImcIccUser(id, imc, icc)


}

