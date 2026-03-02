package com.example.cultura.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cultura.data.repository.BookRepository
import com.example.cultura.data.model.Book
import com.example.cultura.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class BookViewModel : ViewModel() {
    private val bookRepository = BookRepository()

    // LiveData to observe the book data
    val books = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            val response: Response<List<Book>> = bookRepository.searchBooks("your_api_key", "query")
            if (response.isSuccessful) {
                emit(Resource.success(response.body()))
            } else {
                emit(Resource.error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.error(e.localizedMessage ?: "An error occurred"))
        }
    }
}
