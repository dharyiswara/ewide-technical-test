package com.ewide.test.daryhilmyiswara.repository

import com.ewide.test.daryhilmyiswara.database.FavoriteGameDatabase
import com.ewide.test.daryhilmyiswara.datasource.GameDetailDataSource
import com.ewide.test.daryhilmyiswara.model.FavoriteGameTable
import com.ewide.test.daryhilmyiswara.model.GameDetail
import javax.inject.Inject

class GameDetailRepository @Inject constructor(
    private val gameDetailDataSource: GameDetailDataSource,
    private val favoriteGameDatabase: FavoriteGameDatabase
) {

    suspend fun getGameDetail(id: String): GameDetail? = gameDetailDataSource.getGameDetail(id)

    suspend fun getFavoriteMovie(gameId: String): FavoriteGameTable? {
        return favoriteGameDatabase.favoriteGameDao().getSelectedFavoriteGame(gameId).getOrNull(0)
    }

    suspend fun insertFavoriteGame(favoriteGameTable: FavoriteGameTable) {
        favoriteGameDatabase.favoriteGameDao().insertFavorite(favoriteGameTable)
    }

    suspend fun deleteFavoriteGame(gameId: String) {
        favoriteGameDatabase.favoriteGameDao().deleteFavorite(gameId)
    }

}