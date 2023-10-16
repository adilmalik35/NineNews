package com.nine.ninenews.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nine.ninenews.data.model.Asset
import com.nine.ninenews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    // LiveData for storing and observing the list of news articles
    private val _newsArticles = MutableLiveData<List<Asset>>()
    val newsArticles: LiveData<List<Asset>>
        get() = _newsArticles

    // LiveData for indicating loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    // LiveData for handling errors
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    // Function to fetch news articles from the API
    fun fetchNewsArticles() {

        // Show loading indicator
        _isLoading.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            try {

                // Make the API request to fetch news articles
                val response = newsRepository.getNewsArticles()
                val assets = response.body()?.assets

                if (response.isSuccessful && assets != null) {

                    val sortAssets = assets.sortedByDescending { it.timeStamp }

                    // Update the LiveData with the list of news sorted articles
                    _newsArticles.postValue(sortAssets)
                } else {
                    // Handle API error
                        val error = response.errorBody()?.string()
                    _error.postValue(  error ?: "Failed to fetch news articles")
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
                _error.postValue("Error: ${e.message}")
            } finally {
                // Hide loading indicator
                _isLoading.postValue(false)
            }
        }
    }
}