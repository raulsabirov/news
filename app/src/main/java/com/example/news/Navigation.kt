package com.example.news

sealed interface Navigation {
    object Articles : Navigation
    data class ArticleDetail(val id: String) : Navigation
    data class First(val num: Int) : Navigation
    data class Second(val num: Int) : Navigation

}