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

class RoutinesViewModel(private val repository: RoutineRepository): ViewModel(){

    // ---
    // States for the screen
    // ---

    // Inicializadas en % para recibir todos los datos de cada una
    var category = MutableLiveData("%")
    var level = MutableLiveData("%")
    var approach = MutableLiveData("%")


    // ---
    // Routines functions
    // ---

    // Get Routines by query

    // All routines
    @OptIn(ExperimentalPagingApi::class)
    fun getRoutines(query: String) = repository.getRoutinesPage(2, query)

    // Routines by approach
    @OptIn(ExperimentalPagingApi::class)
    fun getRoutinesByApproach(query: String) = repository.getRoutinesPageByAproach(5, query)

    // Routines by approach and category
    @OptIn(ExperimentalPagingApi::class)
    fun getRoutinesByApproachAndCategory(query: String, query2: String) = repository.getRoutinesPageByAproachAndCategory(5, query, query2)

    // Routines by approach and level
    @OptIn(ExperimentalPagingApi::class)
    fun getRoutinesByApproachAndLevel(query: String, query2: String) = repository.getRoutinesPageByAproachAndLevel(5, query, query2)

    // Routines by level
    @OptIn(ExperimentalPagingApi::class)
    fun getRoutinesByLevel(query: String) = repository.getRoutinesPageByLevel(2, query)

    // Routines by level and category and approach
    @OptIn(ExperimentalPagingApi::class)
    fun getRoutinesByApproachAndCategoryAndLevel(query: String, query2: String, query3: String) = repository.getRoutinesPageByApproachAndCategoryAndLevel(4, query, query2, query3)

    // Routines all
    @OptIn(ExperimentalPagingApi::class)
    fun getAllRoutines(query: String) = repository.getAllRoutinesPage(2, query)

    // Creating factory for this viewmodel
    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                RoutinesViewModel(app.routineRepository)
            }
        }
    }

}