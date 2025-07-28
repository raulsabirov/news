package com.example.news.navigation

import androidx.compose.runtime.Composable
import com.example.news.navigation.components.ArticleListComponent
import com.example.news.navigation.components.CustomColumnComponent
import com.example.news.navigation.components.RememberUpdatedStateComponent

interface RenderDelegate<T> {
    @Composable
    fun render(data: T)
}

// Специализированные делегаты для каждого типа экрана
interface ArticleListRenderDelegate : RenderDelegate<ArticleListComponent>
interface CustomColumnRenderDelegate : RenderDelegate<CustomColumnComponent>
interface RememberUpdatedStateRenderDelegate : RenderDelegate<RememberUpdatedStateComponent>
