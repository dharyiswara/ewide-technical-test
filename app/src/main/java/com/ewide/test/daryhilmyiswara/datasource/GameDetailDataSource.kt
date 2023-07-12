package com.ewide.test.daryhilmyiswara.datasource

import com.ewide.test.daryhilmyiswara.model.GameDetail
import com.ewide.test.daryhilmyiswara.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GameDetailDataSource @Inject constructor(
    private val apiService: ApiService
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend fun getGameDetail(id: String): GameDetail? {
        return withContext(coroutineContext) {
            try {
                apiService.getGameDetail(id)
            } catch (e: Exception) {
                null
            }
        }
    }

}