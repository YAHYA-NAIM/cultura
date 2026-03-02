package com.example.cultura.data.remote

import com.example.cultura.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface MovieApiService {
    @GET("?apikey=b499f68d")
    suspend fun getMovies(): Response<MovieResponse>
}


