package com.nine.ninenews.data.repository


import com.nine.ninenews.data.api.NewsApiClient
import com.nine.ninenews.data.model.NewsArticle
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApiClient: NewsApiClient) {

    suspend fun getNewsArticles(): Response<NewsArticle> {
        // Call the NewsApiClient to fetch news articles
        return newsApiClient.fetchNewsArticles()
    }

}