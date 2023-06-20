package com.uca.polifitnessapp.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.uca.polifitnessapp.data.db.models.NoticeModel

@Dao
interface NoticeDao {

    // ---
    // Get all news (Pagination)
    // ---
    @Query("SELECT * FROM notice_table WHERE category like :query")
    fun pagingSource(query: String): PagingSource<Int, NoticeModel>

    // ---
    // Insert new
    // ---
    @Transaction
    @Insert
    suspend fun insertNotice(notice: NoticeModel)

    // ---
    // Get New by category
    // ---
    @Query("SELECT * FROM notice_table WHERE category = :category")
    suspend fun getNoticeByCategory(category: String): List<NoticeModel>

    // ---
    // Get New by id
    // ---
    @Query("SELECT * FROM notice_table WHERE noticeId = :noticeId")
    suspend fun getNoticeById(noticeId: String): NoticeModel?

    // ---
    // Toggle New (hidden)
    // ---
    @Query("UPDATE notice_table SET hidden = :hidden WHERE noticeId = :noticeId")
    suspend fun toggleNotice(noticeId: Int, hidden: Boolean)

    // ---
    // Clear all news
    // ---
    @Query("DELETE FROM notice_table")
    suspend fun clearAll()

    // ---
    // Insert all news
    // ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<NoticeModel>)
}