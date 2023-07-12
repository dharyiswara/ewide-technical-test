package com.ewide.test.daryhilmyiswara.repository

import com.ewide.test.daryhilmyiswara.datasource.GameDetailDataSource
import com.ewide.test.daryhilmyiswara.model.GameDetail
import javax.inject.Inject

class GameDetailRepository @Inject constructor(
    private val gameDetailDataSource: GameDetailDataSource
) {

    suspend fun getGameDetail(id: String): GameDetail? = gameDetailDataSource.getGameDetail(id)

}