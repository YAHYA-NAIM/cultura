package com.example.cultura.data.remote

import com.example.cultura.data.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 10
    ): Response<List<Book>>
}