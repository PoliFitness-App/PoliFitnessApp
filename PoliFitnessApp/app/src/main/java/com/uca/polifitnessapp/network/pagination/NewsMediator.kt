package com.uca.polifitnessapp.network.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.paging.util.getOffset
import androidx.room.withTransaction
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.data.db.models.RemoteKey
import com.uca.polifitnessapp.network.service.NewsService
import com.uca.polifitnessapp.ui.news.viewmodel.NewsScreenViewModel
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsMediator(
    private val database: PoliFitnessDatabase,
    private val service: NewsService,
    private val query: String,
    private val isRefreshState: Boolean
) : RemoteMediator<Int, NoticeModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NoticeModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH ->
                    if (isRefreshState) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    } else {
                       1
                    }
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        database.remoteKeysDao()
                            .remoteKeyByQuery(query)
                    }
                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    remoteKey.nextKey
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = service.getPostsByBlocks(
                page = loadKey, limit = state.config.pageSize
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.noticeDao().clearAll()
                    database.remoteKeysDao()
                        .remoteKeyByQuery(query)
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                database.noticeDao().insertAll(response.posts)
                database.remoteKeysDao().insertOrReplace(
                    RemoteKey(
                        query,
                        if (response.currentPage == response.totalPages) {
                            null
                        } else response.currentPage + 1
                    )
                )
            }

            MediatorResult.Success(
                endOfPaginationReached = response.posts.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}