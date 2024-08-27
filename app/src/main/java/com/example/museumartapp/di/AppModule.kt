package com.example.museumartapp.di

import android.app.Application
import androidx.room.Room
import com.example.museumartapp.Constants.BASE_URL
import com.example.museumartapp.data.ApiService
import com.example.museumartapp.data.local.ArtDatabase
import com.example.museumartapp.data.local.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): ArtDatabase {
        return Room.databaseBuilder(
            context = application,
            ArtDatabase::class.java,
            "artDatabase.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideArtDao(database: ArtDatabase): Dao {
        return database.dao
    }
}