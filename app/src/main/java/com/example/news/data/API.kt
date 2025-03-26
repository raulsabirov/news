package com.example.news.data


import com.example.news.models.ArticleApiModel
import retrofit2.Response

import retrofit2.http.*

interface API {
    @GET("top-headlines")
    suspend fun query(
        @Query("apiKey") apiKey: String = "9e39934e997343cf8a4b6010d533a801",
        @Query("category") category: String = "science",
        @Query("country") ru: String = "ru",
        @Query("pageSize") pageSize: Int = 5,
        @Query("page") page: Int = 1,
        @Query("q") q: String = "",
    ): Response<ArticleApiModel>
}

//https://newsapi.org/v2/everything?q=apple&from=2025-05-14&to=2025-05-14&sortBy=popularity&apiKey=9e39934e997343cf8a4b6010d533a801