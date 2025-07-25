package com.example.news.navigation

import androidx.compose.runtime.Composable

interface RenderDelegate<T> {
    @Composable
    fun render(data: T)
}

// Специализированные делегаты для каждого типа экрана
interface ArticleListRenderDelegate : RenderDelegate<ArticleListComponent>
interface CustomColumnRenderDelegate : RenderDelegate<CustomColumnComponent>
interface RememberUpdatedStateRenderDelegate : RenderDelegate<RememberUpdatedStateComponent>
