package com.uca.polifitnessapp.ui.news.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.ui.login.viewmodel.LoginViewModel

class NewsViewModel(private val repository: NoticeRepository):ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val pokemons = repository.getNewsPage(5)

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
