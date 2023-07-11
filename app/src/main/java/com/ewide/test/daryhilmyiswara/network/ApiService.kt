package com.ewide.test.daryhilmyiswara.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGamesList(@Query("title") title: String, @Query("limit") limit: Int)

    @GET("games")
    suspend fun getGamesById(@Query("id") id: Int)

}