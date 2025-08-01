package news.navigation

import com.example.news.presentation.ArticlesViewModel
import news.navigation.renderers.ArticleListRenderer
import news.navigation.renderers.CustomColumnRenderer
import news.navigation.renderers.RememberUpdatedStateRenderer


class RenderDelegateFactory(
    private val mainViewModel: ArticlesViewModel? = null
) {
    
    fun createArticleListDelegate(): ArticleListRenderDelegate =
        ArticleListRenderer(mainViewModel)
    
    fun createCustomColumnDelegate(): CustomColumnRenderDelegate =
        CustomColumnRenderer()
    
    fun createRememberUpdatedStateDelegate(): RememberUpdatedStateRenderDelegate =
        RememberUpdatedStateRenderer()
}
