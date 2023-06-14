package com.uca.polifitnessapp.ui.news.data

import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NoticeRepository):ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun getNews(query: String = "%") = repository.getNewsPage(10, query)//cambiar aqui el parametro q recibe, pa q no sea quemado

   // private val _news = MutableStateFlow<PagingData<NoticeModel>>(repository.)
    //var news: Flow<PagingData<NoticeModel>> = _news

    /*fun loadNews() {
        viewModelScope.launch {
            @OptIn(ExperimentalPagingApi::class)
            val newsFlow = repository.getNewsPage(10)
            news = newsFlow
        }
    }*/

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                NewsViewModel(app.noticeRepository)
            }
        }
    }
}
