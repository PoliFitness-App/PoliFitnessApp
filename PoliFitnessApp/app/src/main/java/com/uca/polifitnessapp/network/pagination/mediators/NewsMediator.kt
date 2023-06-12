package com.uca.polifitnessapp.network.pagination.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.network.service.NewsService
import com.uca.polifitnessapp.ui.news.data.News

@OptIn(ExperimentalPagingApi::class)
class NewsMediator(private val database: PoliFitnessDatabase, private val service: NewsService): RemoteMediator<Int, News>(){

    override suspend fun load(loadType: LoadType, state: PagingState<Int, News>): MediatorResult {
        TODO()
    }
}