package com.example.cultura.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cultura.data.model.Movie
import com.example.cultura.data.repository.MovieRepository
import com.example.cultura.utils.Resource
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    var popularMovies = mutableStateOf<Resource<List<Movie>>>(Resource.Loading())
        private set

    fun getMovies() {
        viewModelScope.launch {
            popularMovies.value = Resource.Loading()
            try {
                val response = movieRepository.fetchMovies()
                if (response.isSuccessful) {
                    val movieList = response.body()?.Search ?: emptyList()
                    popularMovies.value = Resource.Success(movieList)
                } else {
                    val errorMessage = "API Error: ${response.code()} - ${response.message()}"
                    Log.e("MovieViewModel", errorMessage)
                    popularMovies.value = Resource.Error(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error: ${e.localizedMessage}")
                popularMovies.value = Resource.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}
