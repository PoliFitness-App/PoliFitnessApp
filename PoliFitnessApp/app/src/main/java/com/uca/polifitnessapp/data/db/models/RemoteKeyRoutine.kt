package com.uca.polifitnessapp.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys_routine")
data class RemoteKeyRoutine(@PrimaryKey val label: String, val nextKey: Int?)
