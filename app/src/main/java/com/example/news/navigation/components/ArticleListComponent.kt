package com.example.news.navigation.components

import com.arkivanov.decompose.ComponentContext

interface ArticleListComponent {
    fun onNavigateToCustomColumn()
    fun onNavigateToRememberUpdatedState()
}

class DefaultArticleListComponent(
    componentContext: ComponentContext,
    private val onNavigateToCustomColumn: () -> Unit,
    private val onNavigateToRememberUpdatedState: () -> Unit,
) : ArticleListComponent, ComponentContext by componentContext {

    override fun onNavigateToCustomColumn() {
        onNavigateToCustomColumn.invoke()
    }

    override fun onNavigateToRememberUpdatedState() {
        onNavigateToRememberUpdatedState.invoke()
    }
}
