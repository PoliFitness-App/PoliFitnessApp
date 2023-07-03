package com.uca.polifitnessapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.uca.polifitnessapp.data.db.models.UserModel


@Dao
interface UserDao {

    //Funcion para obtener todos los usuarios
    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserModel>

    // Funcion para eliminar un usuario
    @Query("DELETE FROM user_table WHERE _id = :id")
    suspend fun deleteUser(id: String)

    //Funcion para insertar un usuario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserModel)

    //Funcion para obtener un usuario por id
    @Query("SELECT * FROM user_table WHERE _id = :id")
    suspend fun getUserById(id: String): UserModel?

    //Funcion para actualizar un usuario
    @Update
    suspend fun updateUser(user: UserModel)

    //Funcion para eliminar un usuario
    @Query("SELECT * FROM user_table LIMIT 1")
    suspend fun getUser(): UserModel?

    //Funcion para actualizar IMC e ICC
    @Query("UPDATE user_table SET imc = :imc, icc = :icc WHERE _id = :id")
    suspend fun updateImcIccUser(id: Int, imc: Float, icc: Float)

}