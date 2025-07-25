package com.example.news.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.news.navigation.components.ArticleListComponent
import com.example.news.navigation.components.CustomColumnComponent
import com.example.news.navigation.components.RememberUpdatedStateComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>
    val renderDelegateFactory: RenderDelegateFactory
    
    fun onBackClicked(toIndex: Int)
    
    sealed class Child {
        class ArticleListChild(
            val component: ArticleListComponent,
            val renderDelegate: ArticleListRenderDelegate
        ) : Child()
        
        class CustomColumnChild(
            val component: CustomColumnComponent,
            val renderDelegate: CustomColumnRenderDelegate
        ) : Child()
        
        class RememberUpdatedStateChild(
            val component: RememberUpdatedStateComponent,
            val renderDelegate: RememberUpdatedStateRenderDelegate
        ) : Child()
    }
}
