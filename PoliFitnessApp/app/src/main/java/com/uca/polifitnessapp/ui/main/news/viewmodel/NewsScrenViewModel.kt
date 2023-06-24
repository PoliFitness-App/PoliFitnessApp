package com.uca.polifitnessapp.ui.main.news.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.ui.main.news.status.NewStatusUi

class NewsScreenViewModel(
    private val repository: NoticeRepository
) : ViewModel() {

    // ---
    // States for the screen
    // ---

    var category = MutableLiveData("%")
    // Status
    private var status: NewStatusUi by mutableStateOf(NewStatusUi.Resume)
    // Loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // ---
    // States for the screen
    // ---
    // Selected index
    val selectedIndex = mutableStateOf(0)
    // Scroll state
    var scrollState = mutableStateOf(0)

    // User instance
    var new by mutableStateOf(
        NoticeModel(
            "",
            "",
            "",
            "",
            false
        )
    )
        private set

    // ---
    // News functions
    // ---

    // Get News

    @OptIn(ExperimentalPagingApi::class)
    fun getNews(query: String)=
            repository.getNewsPage(30, query)

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
    // On index change
    // ---
    fun onIndexChange(index: Int) {
        selectedIndex.value = index
    }


    fun onLoadingChange(isLoading: Boolean) {
        _isLoading.value = isLoading
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