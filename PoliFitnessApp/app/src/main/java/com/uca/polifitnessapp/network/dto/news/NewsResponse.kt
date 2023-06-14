package com.uca.polifitnessapp.network.dto.news

import com.uca.polifitnessapp.data.db.models.NoticeModel


data class NewsResponse(
    val posts: List<NoticeModel>,
    val totalPages: Int,
    val currentPage: Int
    )