package com.ewide.test.daryhilmyiswara.network

import com.ewide.test.daryhilmyiswara.model.Games
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGamesList(@Query("title") title: String, @Query("limit") limit: Int): List<Games>

    @GET("games")
    suspend fun getGamesById(@Query("id") id: Int)

}