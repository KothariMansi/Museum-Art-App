package com.example.museumartapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.museumartapp.domain.models.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArt(record: Record)
    @Query("SELECT * FROM Record")
    fun getArt(): Flow<List<Record>>

    @Delete
    suspend fun deleteArt(record: Record)
}