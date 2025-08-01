package news.navigation

import androidx.compose.runtime.Composable
import news.navigation.components.ArticleListComponent
import news.navigation.components.CustomColumnComponent
import news.navigation.components.RememberUpdatedStateComponent

interface RenderDelegate<T> {
    @Composable
    fun render(data: T)
}

// Специализированные делегаты для каждого типа экрана
interface ArticleListRenderDelegate : RenderDelegate<ArticleListComponent>
interface CustomColumnRenderDelegate : RenderDelegate<CustomColumnComponent>
interface RememberUpdatedStateRenderDelegate : RenderDelegate<RememberUpdatedStateComponent>
