package com.example.cultura.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookRetrofitInstance {
    private const val BASE_URL = "\"https://openlibrary.org/api/volumes/brief/json/OL7353617M"

    val apiService: BookApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApiService::class.java)
    }
}
