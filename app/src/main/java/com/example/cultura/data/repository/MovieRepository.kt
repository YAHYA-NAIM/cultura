package com.example.cultura.data.repository

import com.example.cultura.data.model.MovieResponse
import com.example.cultura.data.remote.MovieRetrofitInstance
import retrofit2.Response

class MovieRepository {
    private val apiService = MovieRetrofitInstance.apiService

    // Corrected: Only one class definition for MovieRepository
    suspend fun fetchMovies(): Response<MovieResponse> {
        return apiService.getMovies() // Fetch the list of movies without searching
    }
}

