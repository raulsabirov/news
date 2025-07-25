package com.example.news.navigation

import com.example.news.navigation.renderers.ArticleListRenderer
import com.example.news.navigation.renderers.CustomColumnRenderer
import com.example.news.navigation.renderers.RememberUpdatedStateRenderer
import com.example.news.presentation.MainViewModel

class RenderDelegateFactory(
    private val mainViewModel: MainViewModel? = null
) {
    
    fun createArticleListDelegate(): ArticleListRenderDelegate = 
        ArticleListRenderer(mainViewModel)
    
    fun createCustomColumnDelegate(): CustomColumnRenderDelegate = 
        CustomColumnRenderer()
    
    fun createRememberUpdatedStateDelegate(): RememberUpdatedStateRenderDelegate = 
        RememberUpdatedStateRenderer()
}
