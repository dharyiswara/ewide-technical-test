package com.ewide.test.daryhilmyiswara.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private var favoriteGameDatabase: FavoriteGameDatabase? = null

    @Provides
    fun provideFavoriteGameDatabase(@ApplicationContext context: Context): FavoriteGameDatabase {
        val database = favoriteGameDatabase ?: buildDatabase(context)
        if (favoriteGameDatabase == null) {
            favoriteGameDatabase = buildDatabase(context)
        }
        return database
    }

    private fun buildDatabase(context: Context): FavoriteGameDatabase {
        return Room.databaseBuilder(context, FavoriteGameDatabase::class.java, "favorite_game.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}