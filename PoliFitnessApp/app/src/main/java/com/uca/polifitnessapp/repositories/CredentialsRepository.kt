package com.uca.polifitnessapp.repositories

import com.uca.polifitnessapp.data.db.dao.UserDao
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.network.ApiResponse
import com.uca.polifitnessapp.network.dto.login.LoginRequest
import com.uca.polifitnessapp.network.dto.login.LoginResponse
import com.uca.polifitnessapp.network.dto.register.RegisterRequest
import com.uca.polifitnessapp.network.dto.register.RegisterResponse
import com.uca.polifitnessapp.network.dto.update.UpdateRequest
import com.uca.polifitnessapp.network.dto.update.UpdateResponse
import com.uca.polifitnessapp.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class CredentialsRepository(
    private val api: AuthService,
    private val userDao: UserDao
) {

    // ---
    // User DAO functions
    // ---

    // Function DAO to add a user
    private suspend fun addUser(user: UserModel) =
            userDao.insertUser(user)

    // Function DAO to update a user
    private suspend fun updateUserDAO(user: UserModel) = userDao.updateUser(user)

    // Function DAO to get a user
    suspend fun getUserDAO(): UserModel? = userDao.getUser()

    // Function DAO to get a user by id
    suspend fun getUserById(id: String): UserModel? = userDao.getUserById(id)

    // Function DAO to delete a user
    suspend fun logout(id: String) = userDao.deleteUser(id)

    // ---
    // API functions
    // ---

    // Function to login the user
    // We send the email and password to the api
    suspend fun login(
        email: String,
        password: String
    ): ApiResponse<String> {
        try {
            val response: LoginResponse = api.login(LoginRequest(email, password))
            return ApiResponse.Success(response.token)

        } catch (e: HttpException) {
            if (e.code() == 400) {
                return ApiResponse.ErrorWithMessage("Invalid credentials email or password")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }

    // Function to get the user
    // We get the user from the api
    suspend fun whoami(): ApiResponse<UserModel> {
        try {
            // we get the user from the api (whoami returns a UserModel) information)
            val response: UserModel = api.whoami()
            // we add the user to the database
            addUser(response)
            // we return the user
            return ApiResponse.Success(response)
        } catch (e: HttpException) {
            if (e.code() == 400) {
                return ApiResponse.ErrorWithMessage("Auth error")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }

    // Function to register the user
    // We send data to the api
    suspend fun register(
        username: String,
        lastname: String,
        email: String,
        password: String,
        imc: Float,
        icc: Float,
        gender:Boolean,
        dateOfBirth: String,
        weight: Float,
        height: Float,
        waistP: Float,
        hipP: Float,
        approach: String,
    ): ApiResponse<String> {
        try {
            val response: RegisterResponse =
                api.register(
                    RegisterRequest(
                        username,
                        lastname,
                        email,
                        password,
                        imc,
                        icc,
                        gender,
                        dateOfBirth,
                        weight,
                        height,
                        waistP,
                        hipP,
                        approach
                    )
                )
            return ApiResponse.Success(response.message)

        } catch (e: HttpException) {
            if (e.code() == 409) {
                return ApiResponse.ErrorWithMessage("Ha ocurrido un error al registrar el usuario, el usuario ya existe")
            }
            if (e.code() == 400) {
                return ApiResponse.ErrorWithMessage("Ha ocurrido un error al registrar el usuario, campos incorrectos")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }

    // Function to update the user
    // We send the updated information to the api

    suspend fun updateUser(
        user: UserModel
    ): ApiResponse<String> {
        try {
            val response: UpdateResponse =
                api.update(
                    UpdateRequest(
                        user.weight,
                        user.height,
                        user.waistP,
                        user.hipP,
                        user._id
                    )
                )
            // we update the fields of the user
            user.height = response.height
            user.weight = response.weight
            user.waistP = response.waistP
            user.hipP = response.hipP
            // we update the user in the database
            updateUserDAO(user)
            // we return the response
            return ApiResponse.Success(response.message)

        } catch (e: HttpException) {
            if (e.code() == 400) {
                return ApiResponse.ErrorWithMessage("Ha ocurrido un error al actualizar el usuario")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }
}