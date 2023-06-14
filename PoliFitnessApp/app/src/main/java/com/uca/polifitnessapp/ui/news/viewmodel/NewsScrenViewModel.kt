package com.uca.polifitnessapp.ui.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.ui.news.data.NewsViewModel

class NewsScrenViewModel(
    private val repository: NoticeRepository
) : ViewModel() {
    @OptIn(ExperimentalPagingApi::class)
    fun getNews(query: String = "%") = repository.getNewsPage(2, query)// cambiar aqui el parametro q recibe, pa q no sea quemado

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