package com.uca.polifitnessapp.network.service

import com.uca.polifitnessapp.network.dto.news.NewsRequest
import com.uca.polifitnessapp.network.dto.news.NewsResponse
import com.uca.polifitnessapp.network.dto.news.NewsResponseMsg
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsService {
    //funcion para obtener todos los post
    @GET("api/post")
    suspend fun getPosts(): List<NewsResponse>//TODO habria q cambiarla para recibir por bloques

    //funcion para obtener los post por bloques
    @GET("api/post")
    suspend fun getPostsByBlocks(@Query("page") page: Int, @Query("limit") limit: Int): NewsResponse

    //funcion para crear una noticia, recibe un mensaje de respuesta y se le mandan los datos de la noticia
    @POST("api/post/createPost")
    suspend fun createPost(@Body newsData: NewsRequest): NewsResponseMsg

    //funcion para obtener una noticia por categoria
    @GET("api/post/getPostByCategory")
    suspend fun getPostByCategory(@Query("category") category: String): List<NewsResponse>

    //funcion para obtener una noticia por id
    @PATCH("api/post/getPost/{id}")
    suspend fun getPostById(@Query("id") id: String): NewsResponse

    //funcion para ocultar una noticia por id
    @PATCH("api/post/deletePost/{id}")
    suspend fun hidePostById(@Query("id") id: String): NewsResponseMsg

    //TODO agregar nuevas rutas para usar el pagination

}