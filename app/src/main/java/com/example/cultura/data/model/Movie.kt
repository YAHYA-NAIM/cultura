package com.example.cultura.data.model




data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String
)

data class MovieResponse(
    val Search: List<Movie>?,  // The OMDb API returns a list of movies in 'Search'
    val totalResults: String?
)

