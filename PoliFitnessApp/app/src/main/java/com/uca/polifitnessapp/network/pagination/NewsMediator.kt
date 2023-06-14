package com.uca.polifitnessapp.network.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.data.db.dao.NoticeDao
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.network.service.NewsService
import com.uca.polifitnessapp.ui.news.data.News
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsMediator(private val database: PoliFitnessDatabase, private val service: NewsService): RemoteMediator<Int, NoticeModel>(){

    override suspend fun load(loadType: LoadType, state: PagingState<Int, NoticeModel>): MediatorResult {
        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    2
                    //TODO implementar remote keys para obtener las pagination
                    //crear una nueva tabla para controlar el pagination de news y rutinas
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            println("PARAMETROS Q SE LE PASAN A RESPONSE-------------------------------")
            println("page: $loadKey")
            println("limit: ${state.config.pageSize}")
            val response = service.getPostsByBlocks(
                page = loadKey , limit = state.config.pageSize
            )
            println("RESPONSE---------------------")
            println(response)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.noticeDao().clearAll()
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                println("INSERCION DE TODOS LOS ELEMENTOS EN LA BASE DE DATOS-------------------------------")
                println(response.post)//aqui manda null
                database.noticeDao().insertAll(response.post)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.post.isEmpty()// si esta vacia debe retornar true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }


}