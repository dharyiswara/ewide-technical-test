package com.ewide.test.daryhilmyiswara.repository

import com.ewide.test.daryhilmyiswara.datasource.GamesListDataSource
import com.ewide.test.daryhilmyiswara.model.Games
import javax.inject.Inject

class GamesListRepository @Inject constructor(
    private val gamesListDataSource: GamesListDataSource
) {

    suspend fun getGamesList(title: String, limit: Int): List<Games>? =
        gamesListDataSource.getGamesList(title, limit)

}