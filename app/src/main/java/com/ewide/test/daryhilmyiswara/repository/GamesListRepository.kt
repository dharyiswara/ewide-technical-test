package com.ewide.test.daryhilmyiswara.repository

import com.ewide.test.daryhilmyiswara.datasource.GamesListDataSource
import com.ewide.test.daryhilmyiswara.model.Games
import com.ewide.test.daryhilmyiswara.preference.SortPreference
import javax.inject.Inject

class GamesListRepository @Inject constructor(
    private val gamesListDataSource: GamesListDataSource,
    private val sortPreference: SortPreference
) {

    suspend fun getGamesList(title: String, limit: Int): List<Games>? =
        gamesListDataSource.getGamesList(title, limit)

    fun setIsSortAscending(isSortAscending: Boolean) {
        sortPreference.setIsSortAscending(isSortAscending)
    }

    fun isSortAscending(): Boolean = sortPreference.isSortAscending()

}