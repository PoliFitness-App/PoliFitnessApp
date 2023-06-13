package com.uca.polifitnessapp.repositories

import com.uca.polifitnessapp.data.db.dao.UserDao
import com.uca.polifitnessapp.data.db.models.UserModel
import com.uca.polifitnessapp.network.ApiResponse
import com.uca.polifitnessapp.network.dto.login.LoginRequest
import com.uca.polifitnessapp.network.dto.login.LoginResponse
import com.uca.polifitnessapp.network.dto.register.RegisterRequest
import com.uca.polifitnessapp.network.dto.register.RegisterResponse
import com.uca.polifitnessapp.network.dto.whoami.WhoamiReponse
import com.uca.polifitnessapp.network.service.AuthService
import retrofit2.HttpException
import java.io.IOException

class CredentialsRepository(private val api: AuthService, private val userDao: UserDao) {

    suspend fun addUser(user: UserModel) {
        if (userDao.getUserById(user._id) == null) {
            userDao.insertUser(user)
        }
    }

    suspend fun login(email: String, password: String): ApiResponse<String> {
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

    suspend fun whoami(): ApiResponse<UserModel> {
        try {
            val response: UserModel = api.whoami()
            addUser(response)
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


    suspend fun register(name: String, email: String, password: String): ApiResponse<String> {
        try {
            val response: RegisterResponse =
                api.register(RegisterRequest(name, email, password))
            return ApiResponse.Success(response.msg)

        } catch (e: HttpException) {
            if (e.code() == 400) {
                return ApiResponse.ErrorWithMessage("email already exists")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }
}