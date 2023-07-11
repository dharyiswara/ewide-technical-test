package com.ewide.test.daryhilmyiswara.datasource

import com.ewide.test.daryhilmyiswara.model.Games
import com.ewide.test.daryhilmyiswara.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GamesListDataSource @Inject constructor(
    private val apiService: ApiService
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend fun getGamesList(title: String, limit: Int): List<Games>? {
        return withContext(coroutineContext) {
            try {
                apiService.getGamesList(title, limit)
            } catch (e: Exception) {
                null
            }
        }
    }

}