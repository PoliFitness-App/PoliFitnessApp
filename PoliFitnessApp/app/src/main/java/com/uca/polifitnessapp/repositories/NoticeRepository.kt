package com.uca.polifitnessapp.repositories

import com.uca.polifitnessapp.data.db.dao.NoticeDao
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.network.retrofit.RetrofitInstance

class NoticeRepository(private val noticeDao: NoticeDao, private val retrofitInstance: RetrofitInstance) {
    //Obtener todas las funciones provenientes del dao
    suspend fun getAllNotices() = noticeDao.getAllNotices()

    suspend fun insertNotice(notice: NoticeModel) = noticeDao.insertNotice(notice)

    suspend fun getNoticeByCategory(category: String) = noticeDao.getNoticeByCategory(category)

    suspend fun getNoticeById(noticeId: Int) = noticeDao.getNoticeById(noticeId)

    suspend fun toggleNotice(noticeId: Int, hidden: Boolean) = noticeDao.toggleNotice(noticeId, hidden)

    //TODO: agregar el repositorio al application luego de tener la base de datos creada
}