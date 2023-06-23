package com.uca.polifitnessapp.ui.main.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.data.db.models.NoticeModel
import com.uca.polifitnessapp.data.db.models.routine.RoutineModel
import com.uca.polifitnessapp.data.db.models.routine.StepModel
import com.uca.polifitnessapp.repositories.NoticeRepository
import com.uca.polifitnessapp.repositories.RoutineRepository
import com.uca.polifitnessapp.ui.main.home.state.HomeUiStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val newsRepository: NoticeRepository,
    private val routineRepository: RoutineRepository
) : ViewModel() {
    /**
     * UI State
     */
    private val _uiState = MutableStateFlow<HomeUiStatus>(HomeUiStatus.Resume)
    val uiState: StateFlow<HomeUiStatus> = _uiState.asStateFlow()

    init {
        _uiState.value = HomeUiStatus.Resume
        fetchNews(3)
        fetchRoutines(3)
    }

    /*
     * News
     */

    var news = listOf(
        NoticeModel(
            "",
            "",
            "",
            "",
            "",
            false
        )
    )
        private set

    /*
    * Routines
     */

    var routines = listOf(
        RoutineModel(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            false,
            "",
            listOf(StepModel("", ""))
        )
    )
        private set

    /**
     * Get 3 routines
     */

    fun fetchRoutines(count: Int) {
        viewModelScope.launch {

            _uiState.value = HomeUiStatus.Loading
            try {
                routines = routineRepository.getRoutines(count)
                _uiState.value = HomeUiStatus.Success("Routines fetched successfully")
            } catch (e: Exception) {
                _uiState.value = HomeUiStatus.Error(e)
            }
        }
    }

    /**
     * Get 3 news
     */

    fun fetchNews(count: Int) {
        viewModelScope.launch {

            _uiState.value = HomeUiStatus.Loading
            try {
                news = newsRepository.getNews(count)
                _uiState.value = HomeUiStatus.Success("News fetched successfully")
            } catch (e: Exception) {
                _uiState.value = HomeUiStatus.Error(e)
            }
        }
    }

    /**
     * Companion object to create the Factory
     */

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                HomeScreenViewModel(app.noticeRepository, app.routineRepository)
            }
        }
    }
}
