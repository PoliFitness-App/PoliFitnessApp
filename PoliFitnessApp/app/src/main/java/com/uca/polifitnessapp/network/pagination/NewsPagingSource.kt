package com.uca.polifitnessapp.network.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.network.service.NewsService
import com.uca.polifitnessapp.ui.news.data.News
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(private val service: NewsService): PagingSource<Int, NoticeModel>(){

    override fun getRefreshKey(state: PagingState<Int, NoticeModel>): Int? {
        val pageSize = state.config.pageSize
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(pageSize) ?: anchorPage?.nextKey?.minus(pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, NoticeModel> {
        return try {
            val nextPage = params.key ?: 1
            val pageSize = params.loadSize
            val newsResult = service
                .getPostsByBlocks(nextPage, pageSize)//page, limit

            //cambiar a newsModel
            val newsResultModel: List<NoticeModel> = newsResult.map {new ->
                NoticeModel(
                    new.tittle,
                    new.description,
                    new.image,
                    new.category,
                    new.hidden
                )
            }


            LoadResult.Page(
                data = newsResultModel,
                nextKey = null,//solo hacia adelante
                prevKey = nextPage
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

}