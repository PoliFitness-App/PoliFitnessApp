package com.uca.polifitnessapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uca.polifitnessapp.data.db.models.RemoteKey
import com.uca.polifitnessapp.data.db.models.RemoteKeyRoutine

@Dao
interface RemoteKeyRoutineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeyRoutine)

    @Query("SELECT * FROM remote_keys_routine WHERE label = :query")
    suspend fun remoteKeyByQuery(query: String): RemoteKeyRoutine

    @Query("DELETE FROM remote_keys_routine WHERE label = :query")
    suspend fun deleteByQuery(query: String)
}