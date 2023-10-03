package com.example.news.data

import com.example.news.models.Article
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource{
     fun getArticles(page: Int): Flow<List<Article>>

}