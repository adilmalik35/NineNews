package com.nine.ninenews.data.api

import com.nine.ninenews.data.model.NewsArticle
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject


interface NewsApiService {
    @GET("alfred_live/67184313/offline/afr/")
    suspend fun getNewsArticles(): Response<NewsArticle>
}

class NewsApiClient @Inject constructor() {
    private val BASE_URL = "https://bruce-v2-mob.fairfaxmedia.com.au/1/"

    // Create a Retrofit instance with Gson converter factory
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of the NewsApiService
    private val newsApiService = retrofit.create(NewsApiService::class.java)

    // Function to fetch news articles
    suspend fun fetchNewsArticles(): Response<NewsArticle> {
        return newsApiService.getNewsArticles()
    }
}