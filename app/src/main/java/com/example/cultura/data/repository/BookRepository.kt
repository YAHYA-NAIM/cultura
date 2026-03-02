package com.example.cultura.data.repository

import com.example.cultura.data.remote.BookRetrofitInstance
import com.example.cultura.data.model.Book
import retrofit2.Response

class BookRepository {
    private val bookApiService = BookRetrofitInstance.apiService

    suspend fun searchBooks(apiKey: String, query: String): Response<List<Book>> {
        return bookApiService.searchBooks(apiKey, query)
    }
}
