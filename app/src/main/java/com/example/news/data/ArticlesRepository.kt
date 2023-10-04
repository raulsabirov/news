package com.example.news.data

import com.example.news.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    fun getArticles(page: Int): Flow<List<Article>>

    suspend fun getArticles2(page: Int): List<Article>
    suspend fun getArticleByTitle(): Article
}