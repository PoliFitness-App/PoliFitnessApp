package com.uca.polifitnessapp.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.uca.polifitnessapp.data.db.PoliFitnessDatabase
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.network.pagination.NewsMediator
import com.uca.polifitnessapp.network.service.NewsService

class NoticeRepository(private val database: PoliFitnessDatabase, private val retrofitInstance: NewsService) {
    private val noticeDao = database.noticeDao()

    //Obtener todas las funciones provenientes del dao
    // TODO: implementar la funcion de obtener todas las noticias con el pagination
    //suspend fun getAllNotices() = noticeDao.getAllNotices()

    suspend fun insertNotice(notice: NoticeModel) = noticeDao.insertNotice(notice)

    suspend fun getNoticeByCategory(category: String) = noticeDao.getNoticeByCategory(category)

    suspend fun getNoticeById(noticeId: Int) = noticeDao.getNoticeById(noticeId)

    suspend fun toggleNotice(noticeId: Int, hidden: Boolean) = noticeDao.toggleNotice(noticeId, hidden)

    //TODO: agregar pagination
    //TODO cambiar en la api las rutas para agregar pagination

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

    /**
     * FORMA EN LA SE DDEBE LLAMAR DENTRO DEL ELEMENTO COMPOSE
     *val news = viewModel.getNews("%").collectAsLazyPagingItems()

    LazyColumn(
    modifier = Modifier
    .fillMaxSize()
    ) {
    println("IMPRESION DE LAS NOTICIAS----------------------------------")
    println(news)
    items(count = news.itemCount) { index ->
    val item = news[index]
    if (item != null) {
    Box(
    modifier = Modifier
    .fillMaxSize()
    ){
    Text(
    text = item.title,

    modifier = Modifier
    .fillMaxSize()
    )
    }
    }
    }
    println("TERMINA LA IMPRESION DE LAS NOTICIAS--------------------------------")
    }
     *
     * */


    /*
    * DATA Q PONER EN EL VIEWMODEL DE NOTICIAS
    * @OptIn(ExperimentalPagingApi::class)
    fun getNews(query: String = "%") = repository.getNewsPage(2, query)// cambiar aqui el parametro q recibe, pa q no sea quemado
    * */
}