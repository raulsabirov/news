package news.navigation

import androidx.compose.runtime.Composable
import news.Articles.ArticleListComponent
import news.navigation.components.CustomColumnComponent

interface RenderDelegate<T> {
    @Composable
    fun render(data: T)
}

