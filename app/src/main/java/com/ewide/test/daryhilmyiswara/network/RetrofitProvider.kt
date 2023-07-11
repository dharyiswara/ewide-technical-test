package com.ewide.test.daryhilmyiswara.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider(private val okHttpClient: OkHttpClient) {
    private var retrofit: Retrofit? = null
    private var apiService: ApiService? = null

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.cheapshark.com/api/1.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        val retrofit = this.retrofit ?: buildRetrofit()
        if (this.retrofit == null) {
            this.retrofit = buildRetrofit()
        }
        return retrofit
    }

    private fun buildApiService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }

    fun getApiService(): ApiService {
        val apiService = this.apiService ?: buildApiService()
        if (this.apiService == null) {
            this.apiService = apiService
        }
        return apiService
    }
}