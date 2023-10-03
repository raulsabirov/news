package com.example.news.models

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class Source(
    val id: String,
    val name: String
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
