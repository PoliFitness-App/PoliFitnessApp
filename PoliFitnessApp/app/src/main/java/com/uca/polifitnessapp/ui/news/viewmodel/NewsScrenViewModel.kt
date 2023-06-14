package com.uca.polifitnessapp.ui.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.ui.news.data.NewsViewModel

class NewsScreenViewModel(
    private val repository: NoticeRepository
) : ViewModel() {

    // ---
    // States for the screen
    // ---

    var category = MutableLiveData("%")


    // ---
    // News functions
    // ---

    // Get News
    @OptIn(ExperimentalPagingApi::class)
    fun getNews(query: String) = repository.getNewsPage(5, query)

    // On category change
    fun onCategoryChange(index:Int){
        when (index) {
            0 -> {
                // filter news by category "General"
                category.value = "%"
            }

            1 -> {
                // filter news by category "Futbol"
                category.value = "Futbol"
            }

            2 -> {
                // filter news by category "Basketball"
                category.value = "Basketball"
            }

            3 -> {
                // filter news by category "Volleyball"
                category.value = "Volleyball"
            }

            4 -> {
                // filter news by category "Actividades"
                category.value = "Actividades"
            }
        }
    }

    // ---
    // Companion object
    // ---

    // Initialize the factoy
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                NewsScreenViewModel(app.noticeRepository)
            }
        }
    }
}