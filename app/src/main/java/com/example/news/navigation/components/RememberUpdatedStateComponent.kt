package com.example.news.navigation.components

import com.arkivanov.decompose.ComponentContext

interface RememberUpdatedStateComponent {
    fun onNavigateBack()
}

class DefaultRememberUpdatedStateComponent(
    componentContext: ComponentContext,
    private val onNavigateBack: () -> Unit,
) : RememberUpdatedStateComponent, ComponentContext by componentContext {

    override fun onNavigateBack() {
        onNavigateBack.invoke()
    }
}
