package com.ewide.test.daryhilmyiswara.network

import androidx.viewbinding.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val retrofitProvider by lazy {
        RetrofitProvider(getOkHttpClient())
    }

    @Provides
    fun provideApiService(): ApiService {
        return retrofitProvider.getApiService()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            addInterceptor(createRequestInterceptor())
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    private fun createRequestInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .header("accept", "application/json")

            val modifiedUrl = original.url.newBuilder()
                .build()

            val request = requestBuilder.url(modifiedUrl)
                .build()

            return@Interceptor chain.proceed(request)
        }
    }

}