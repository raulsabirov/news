package com.example.news

sealed interface Navigation {
    object Articles : Navigation
    data class ArticleDetail(val id: String) : Navigation
    object First : Navigation
    object Second : Navigation

}