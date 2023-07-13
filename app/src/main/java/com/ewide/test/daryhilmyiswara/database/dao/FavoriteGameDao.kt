package com.ewide.test.daryhilmyiswara.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ewide.test.daryhilmyiswara.model.FavoriteGameTable

@Dao
interface FavoriteGameDao {

    @Query("SELECT * FROM favorite_game")
    suspend fun getFavoriteGame(): List<FavoriteGameTable>

    @Query("SELECT * FROM favorite_game WHERE id = :gameId")
    suspend fun getSelectedFavoriteGame(gameId: String): List<FavoriteGameTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteGameTable)

    @Query("DELETE FROM favorite_game WHERE id = :gameId")
    suspend fun deleteFavorite(gameId: String)

}