package com.uca.polifitnessapp.ui.routines.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.repositories.RoutineRepository
import com.uca.polifitnessapp.ui.news.viewmodel.NewsScreenViewModel

class RoutinesViewModel(private val repository: RoutineRepository) : ViewModel() {

    // ---
    // States for the screen
    // ---

    // Inicializadas en % para recibir todos los datos de cada una

    var category = MutableLiveData("%")
    var level = MutableLiveData("%")
    var approach = MutableLiveData("%")
    var scrollState = MutableLiveData(0)

    // ---
    // Routines functions
    // ---

    // On Category change
    fun onCategoryChange(label: String) {
        when (label) {
            "Todos" -> {
                category.value = "%"
            }

            "Tren superior" -> {
                category.value = "Tren superior"
            }

            "Tren inferior" -> {
                category.value = "Tren inferior"
            }

            "Cuerpo completo" -> {
                category.value = "Cuerpo completo"
            }
        }
    }

    //// On level change
    fun onLevelChange(label: String) {
        when (label) {
            "Todos" -> {
                level.value = "%"
            }

            "Fácil" -> {
                level.value = "Fácil"
            }

            "Medio" -> {
                level.value = "Medio"
            }

            "Difícil" -> {
                level.value = "Difícil"
            }

            "Muy difícil" -> {
                level.value = "Muy difícil"
            }
        }
    }

    fun onScrollStateChange(state: Int) {
        scrollState.value = state
    }

    // Get Routines by query
    // Routines by level and category and approach
    @OptIn(ExperimentalPagingApi::class)
    fun getRoutinesByApproachAndCategoryAndLevel(query: String, query2: String, query3: String) =
        repository.getRoutinesPageByApproachAndCategoryAndLevel(4, query, query2, query3)

    // Creating factory for this viewmodel
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                RoutinesViewModel(app.routineRepository)
            }
        }
    }
}