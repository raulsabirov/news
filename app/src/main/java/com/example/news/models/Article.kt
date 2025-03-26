package com.example.news.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Article(
    val source: Source? = Source(),
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
){
    @Parcelize
    companion object : Parcelable {


        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }
    }
}
open class A (){
    @Parcelize
    companion object : Parcelable {

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }
    }

}

data class Source(
    val id: String? = null,
    val name: String? = null
) : A()
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
