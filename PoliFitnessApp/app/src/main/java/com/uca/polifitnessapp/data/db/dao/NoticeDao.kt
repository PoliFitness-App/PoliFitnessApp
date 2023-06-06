package com.uca.polifitnessapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.uca.polifitnessapp.data.db.models.NoticeModel

@Dao
interface NoticeDao {

    //Funcion para obtener todas las noticias
    @Query("SELECT * FROM notice_table")
    suspend fun getAllNotices(): List<NoticeModel>

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

}