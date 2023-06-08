package com.uca.polifitnessapp.ui.news.data

import com.uca.polifitnessapp.R

// News dummy data with title, description, category and image url

data class News(
    val title: String,
    val description: String,
    val category: String,
    val image: Int
)

// List of news

val newsList = listOf<News>(
    News(
        title = "UCA vs Universidad Jose Matias Delgado",
        description = "Marcador : 2 (UCA) - 0 (UJMD).",
        category = "Futbol femenino",
        image = R.drawable.new_1
    ),
    News(
        title = "UCA vs UPED",
        description = "22 de Marzo 12:00 am.Lugar: Centro deportivo.",
        category = "Futbol masculino",
        image = R.drawable.new_2
    ),
    News(
        title = "UCA vs Universidad Jose Matias Delgado",
        description = "Marcador : 2 (UCA) - 0 (UJMD).",
        category = "Futbol femenino",
        image = R.drawable.new_1
    ),
    News(
        title = "UCA vs Universidad Jose Matias Delgado",
        description = "Marcador : 2 (UCA) - 0 (UJMD).",
        category = "Futbol femenino",
        image = R.drawable.new_2
    )
)