package com.mrkumar.workoutapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("Select * from `historyDataTable`")
    fun fetchALlDates(): Flow<List<HistoryEntity>>
}