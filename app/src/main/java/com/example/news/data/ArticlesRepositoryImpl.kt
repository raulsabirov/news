package com.example.news.data

import com.example.news.models.Article
import kotlinx.coroutines.flow.Flow


class ArticlesRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : ArticlesRepository {

    override fun getArticles(page: Int): Flow<List<Article>> {
        return networkDataSource.getArticles(page = page)
    }

    override suspend fun getArticles2(page: Int): List<Article> {
        return networkDataSource.getArticles2(page = page)
    }

    override suspend fun getArticleByTitle(): Article {
        TODO("Not yet implemented")
    }


}