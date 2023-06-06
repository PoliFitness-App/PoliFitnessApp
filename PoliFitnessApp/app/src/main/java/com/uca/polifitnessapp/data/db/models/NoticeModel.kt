package com.uca.polifitnessapp.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "notice_table")
data class NoticeModel(
    @PrimaryKey(autoGenerate = true) val noticeId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "hidden") val hidden: Boolean
){
    constructor(title: String, description: String, image: String, category: String, hidden: Boolean) :
            this(0, title, description, image, category, hidden)
}
