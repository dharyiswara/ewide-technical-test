package com.ewide.test.daryhilmyiswara.repository

import com.ewide.test.daryhilmyiswara.database.FavoriteGameDatabase
import com.ewide.test.daryhilmyiswara.model.FavoriteGameTable
import javax.inject.Inject

class FavoriteGameRepository @Inject constructor(
    private val favoriteGameDatabase: FavoriteGameDatabase
) {

    suspend fun getFavoriteGames(): List<FavoriteGameTable> =
        favoriteGameDatabase.favoriteGameDao().getFavoriteGame()

}