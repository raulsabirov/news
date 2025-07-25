package com.example.news.navigation.components

import com.arkivanov.decompose.ComponentContext

interface CustomColumnComponent {
    fun onNavigateBack()
}

class DefaultCustomColumnComponent(
    componentContext: ComponentContext,
    private val onNavigateBack: () -> Unit,
) : CustomColumnComponent, ComponentContext by componentContext {

    override fun onNavigateBack() {
        onNavigateBack.invoke()
    }
}
