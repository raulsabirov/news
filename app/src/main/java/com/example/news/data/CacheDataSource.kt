package com.example.news.data

import com.example.news.models.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheDataSource @Inject constructor() : LocalDataSource {
    private var articlesCache: List<Article> = listOf()

    override suspend fun getArticles(): Flow<List<Article>> {
        return flow {
            emit(articlesCache)
            delay(1)
        }
    }

    override suspend fun saveArticles(cache: List<Article>) {
        articlesCache = cache
    }


}