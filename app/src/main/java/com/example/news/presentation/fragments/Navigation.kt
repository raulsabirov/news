package com.example.news.presentation.fragments

sealed interface Navigation {
    object Articles : Navigation
    data class ArticleDetail(val id: String) : Navigation
    object First : Navigation
    object Second : Navigation

}