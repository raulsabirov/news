package com.example.news.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun RootContent(component: RootComponent) {
    val stack by component.stack.subscribeAsState()

    Children(
        stack = stack,
        modifier = Modifier.fillMaxSize(),
    ) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.ArticleListChild -> {
                // Делегируем рендеринг специализированному компоненту
                instance.renderDelegate.render(instance.component)
            }
            
            is RootComponent.Child.CustomColumnChild -> {
                // Делегируем рендеринг специализированному компоненту
                instance.renderDelegate.render(instance.component)
            }
            
            is RootComponent.Child.RememberUpdatedStateChild -> {
                // Делегируем рендеринг специализированному компоненту
                instance.renderDelegate.render(instance.component)
            }
        }
    }
}
