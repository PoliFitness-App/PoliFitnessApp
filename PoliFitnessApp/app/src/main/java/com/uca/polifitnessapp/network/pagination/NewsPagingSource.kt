package com.uca.polifitnessapp.network.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.uca.polifitnessapp.network.service.NewsService
import com.uca.polifitnessapp.ui.news.data.News
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(private val service: NewsService): PagingSource<Int, News>(){

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        val pageSize = state.config.pageSize
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(pageSize) ?: anchorPage?.nextKey?.minus(pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, News> {
        return try {
            val nextPage = params.key ?: 1
            val pageSize = params.loadSize
            val newsResult = service//agregar en base la peticion desde la api, agregado en la interfaz del servicio
                .getPokemons(pageSize, nextPage)
            LoadResult.Page(
                data = newsResult.results,
                nextKey = if (newsResult.next != null) nextPage + pageSize else null,
                prevKey = if (newsResult.previous != null) nextPage - pageSize else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    //-----------------------------------REVISAR DESPUES------------------------------------

    /***
     *
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
    return try {
    //TODO implementar en base a la url de la api
    val nextPage = params.key ?: 1
    val response = service.getNews(nextPage)
    val news = response.body()?.news
    LoadResult.Page(
    data = news!!,
    prevKey = if (nextPage == 1) null else nextPage - 1,
    nextKey = if (news.isEmpty()) null else nextPage + 1
    )
    } catch (e: Exception) {
    LoadResult.Error(e)
    }
    }
     */
    /
}