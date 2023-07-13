package com.ewide.test.daryhilmyiswara.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewide.test.daryhilmyiswara.database.dao.FavoriteGameDao
import com.ewide.test.daryhilmyiswara.model.FavoriteGameTable

@Database(
    entities = [FavoriteGameTable::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteGameDatabase : RoomDatabase() {

    abstract fun favoriteGameDao(): FavoriteGameDao

}