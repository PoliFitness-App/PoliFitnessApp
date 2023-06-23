package com.uca.polifitnessapp.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.uca.polifitnessapp.data.PoliFitnessDatabase
import com.uca.polifitnessapp.network.pagination.NewsMediator
import com.uca.polifitnessapp.network.service.NewsService

class NoticeRepository(
    private val database: PoliFitnessDatabase,
    private val retrofitInstance: NewsService
) {
    private val noticeDao = database.noticeDao()
    suspend fun getNoticeById(noticeId: String) = noticeDao.getNoticeById(noticeId)

    suspend fun getNews(count:Int) = noticeDao.getNews(count)

    // --
    // Get new with pagination
    // --
    @ExperimentalPagingApi
    fun getNewsPage(
        pageSize: Int,
        query: String,
    ) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        remoteMediator = NewsMediator(database, retrofitInstance, query),
    ) {
        noticeDao.pagingSource(query)
    }.flow
}