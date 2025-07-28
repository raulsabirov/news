package com.example.news.models


import androidx.compose.runtime.Immutable
import com.example.news.generateUUID
import okio.Source


@Immutable
data class Article2(
    //val id: String =  UUID.randomUUID().toString(),
    val id: String =  generateUUID(),
    val source: Source2? = Source2(),
    val author: String? = null,
    val title: String,
    val description: String,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

@Immutable
data class Source2 (
    val id: String? = null,
    val name: String? = null
)



/*
{
    "source": {
    "id": "google-news",
    "name": "Google News"
            },
    "author": "Интерфакс",
    "title": "Объявлены лауреаты Нобелевской премии по медицине и физиологии - Интерфакс",
    "description": null,
    "url": "https://news.google.com/rss/articles/CBMiJGh0dHBzOi8vd3d3LmludGVyZmF4LnJ1L3dvcmxkLzkyMzY3OdIBAA?oc=5",
    "urlToImage": null,
    "publishedAt": "2023-10-02T09:47:00Z",
    "content": null
}*/
