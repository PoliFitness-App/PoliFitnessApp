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
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsMediator(private val database: PoliFitnessDatabase, private val service: NewsService, private val query: String): RemoteMediator<Int, NoticeModel>(){

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
                    //TODO implementar remote keys para obtener las pagination
                    //crear una nueva tabla para controlar el pagination de news y rutinas

                    // test de paginas sacadas de la api
                    val remoteKey = database.withTransaction {
                        // TODO consultar si esta bien hecho, por q en este caso esta guardando: % = num, futbol = num2, y no se puede compartir con otro remote mediator
                        database.remoteKeysDao().remoteKeyByQuery(query)// le mando la query para saber a q clave remota acceder
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
                page = loadKey , limit = state.config.pageSize
            )


            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.noticeDao().clearAll()
                    database.remoteKeysDao().remoteKeyByQuery(query)// le mando la query para saber a q clave remota acceder
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                database.noticeDao().insertAll(response.posts)
                database.remoteKeysDao().insertOrReplace(
                    RemoteKey(query ,
                    if(response.currentPage == response.totalPages){// si la pagina actual es igual al numero de paginas, estamos en la ultima pagina
                        null
                    }else response.currentPage + 1)// la siguiente pagina seria la actual mas 1
                )
            }

            MediatorResult.Success(
                endOfPaginationReached = response.posts.isEmpty()// si esta vacia debe retornar true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }


}