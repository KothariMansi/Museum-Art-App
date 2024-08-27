package com.example.museumartapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.museumartapp.domain.models.Record

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class ArtDatabase: RoomDatabase() {
    abstract val dao: Dao
}