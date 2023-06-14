package com.uca.polifitnessapp.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.uca.polifitnessapp.data.db.models.NoticeModel

@Dao
interface NoticeDao {

    //Funcion para obtener todas las noticias
    @Query("SELECT * FROM notice_table")//cambiar por el de pagination
    suspend fun getAllNotices(): List<NoticeModel>

    //funcion para obtener todas las noticias por bloques
    @Query("SELECT * FROM notice_table WHERE category like :query")
    fun pagingSource(query: String): PagingSource<Int, NoticeModel>


    //Funcion para insertar una noticia
    @Transaction
    @Insert
    suspend fun insertNotice(notice: NoticeModel)

    //Funcion para obtener noticias por categoria
    @Query("SELECT * FROM notice_table WHERE category = :category")
    suspend fun getNoticeByCategory(category: String): List<NoticeModel>

    //Funcion para obtener una noticia por id
    @Query("SELECT * FROM notice_table WHERE noticeId = :noticeId")
    suspend fun getNoticeById(noticeId: Int): NoticeModel?

    //Funcion de toggle para ocultar una noticia
    @Query("UPDATE notice_table SET hidden = :hidden WHERE noticeId = :noticeId")
    suspend fun toggleNotice(noticeId: Int, hidden: Boolean)

    // funcion para el pagination limpiar base
    @Query("DELETE FROM notice_table")
    suspend fun clearAll()

    // funcion para el pagination insertar lista
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<NoticeModel>)



}