package com.uca.polifitnessapp.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.network.pagination.NewsMediator
import com.uca.polifitnessapp.network.service.NewsService

class NoticeRepository(
    private val database: PoliFitnessDatabase,
    private val retrofitInstance: NewsService
) {
    private val noticeDao = database.noticeDao()

    suspend fun insertNotice(notice: NoticeModel) = noticeDao.insertNotice(notice)

    suspend fun getNoticeByCategory(category: String) = noticeDao.getNoticeByCategory(category)

    suspend fun getNoticeById(noticeId: String) = noticeDao.getNoticeById(noticeId)

    // --
    // Get new with pagination
    // --
    @ExperimentalPagingApi
    fun getNewsPage(pageSize: Int, query: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = NewsMediator(database, retrofitInstance, query)
    ) {
        // recibe la query q se le manda desde la vista
        noticeDao.pagingSource(query)
    }.flow
}