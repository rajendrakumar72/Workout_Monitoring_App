package com.mrkumar.workoutapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historyDataTable")
data class HistoryEntity(
    @PrimaryKey
    val date:String
)
