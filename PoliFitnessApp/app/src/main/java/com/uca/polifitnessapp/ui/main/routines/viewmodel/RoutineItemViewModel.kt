package com.uca.polifitnessapp.ui.main.routines.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.uca.polifitnessapp.PoliFitnessApplication
import com.uca.polifitnessapp.data.db.models.routine.RoutineModel
import com.uca.polifitnessapp.data.db.models.routine.StepModel
import com.uca.polifitnessapp.repositories.RoutineRepository
import com.uca.polifitnessapp.ui.main.news.status.NewStatusUi
import kotlinx.coroutines.launch

class RoutineItemViewModel(
    private val repository: RoutineRepository
) : ViewModel() {

    // ---
    // States for the screen
    // ---

    val isLoading = mutableStateOf(false)
    private var status: NewStatusUi by mutableStateOf(NewStatusUi.Resume)

    // Routine instance
    var routine by mutableStateOf(
        RoutineModel(
            "",
            "",
            "",
            "",
            "",
            "",
            false,
            "",
            listOf()
        )
    )
        private set

    // ---
    // Routines functions
    // ---

    fun fetchRoutineById(id: String) {

        viewModelScope.launch {
            setLoading(true)
            try {
                // Call repository function
                val routineA = repository.getRoutineById(id)
                routine = routineA!!
                // Set success status
                status = NewStatusUi.Success("Success")
            } catch (e: Exception) {

                // Set error status
                status = NewStatusUi.Error(e)
            }
            setLoading(false)
        }
    }

    // ---
    // Loading functions
    // ---

    private var exoPlayer: ExoPlayer? = null

    val player: ExoPlayer?
        get() = exoPlayer

    fun initializePlayer(context: Context, url: String) {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build()
            val mediaItem = MediaItem.fromUri(Uri.parse(url))
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.prepare()
        }
    }

    fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }

    private fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

    // Creating factory for this viewModel
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PoliFitnessApplication
                RoutineItemViewModel(app.routineRepository)
            }
        }
    }
}