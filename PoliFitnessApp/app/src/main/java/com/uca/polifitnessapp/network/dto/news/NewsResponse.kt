package com.uca.polifitnessapp.network.dto.news

import com.uca.polifitnessapp.data.db.models.NoticeModel


data class NewsResponse( val post: List<NoticeModel>)