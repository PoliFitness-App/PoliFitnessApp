package com.uca.polifitnessapp.network.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.uca.polifitnessapp.data.PoliFitnessDatabase
import com.uca.polifitnessapp.data.db.models.RemoteKeyRoutine
import com.uca.polifitnessapp.data.db.models.routine.RoutineModel
import com.uca.polifitnessapp.network.service.RoutineService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RoutinesMediator(private val database: PoliFitnessDatabase, private val service: RoutineService, private val query: String): RemoteMediator<Int, RoutineModel>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, RoutineModel>): MediatorResult {
        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {

                    // test de paginas sacadas de la api
                    val remoteKey = database.withTransaction {
                        database.remoteKeysRoutineDao().remoteKeyByQuery(query)// le mando la query para saber a q clave remota acceder
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



            val response = service.getRoutinesByBlocks(
                page = loadKey , limit = state.config.pageSize
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.routineDao().clearAll()
                    database.remoteKeysRoutineDao().remoteKeyByQuery(query)// le mando la query para saber a q clave remota acceder
                }


                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                database.routineDao().insertAll(response.routines)
                database.remoteKeysRoutineDao().insertOrReplace(
                    RemoteKeyRoutine(query ,
                        if(response.currentPage == response.totalPages){// si la pagina actual es igual al numero de paginas, estamos en la ultima pagina
                            null
                        }else response.currentPage + 1)// la siguiente pagina seria la actual mas 1
                )
            }

            MediatorResult.Success(
                endOfPaginationReached = response.routines.isEmpty()// si esta vacia debe retornar true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }

}