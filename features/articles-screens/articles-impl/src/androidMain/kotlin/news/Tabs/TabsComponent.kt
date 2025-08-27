package news.Tabs

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import news.Articles.ArticleListComponent
import news.RememberUpdatedState.RememberUpdatedStateComponent
import news.navigation.RenderDelegate
import news.navigation.components.CustomColumnComponent

interface TabsComponent {

    val stack: Value<ChildStack<*, Child>>
    fun onArticlesTabClicked()
    fun onCustomColumnTabClicked()
    fun onRememberUpdatedStateTabClicked()


    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class ArticlesChild(
            val component: ArticleListComponent,
            val renderDelegate: RenderDelegate<ArticleListComponent>
        ) : Child()

        class CustomColumnChild(
            val component: CustomColumnComponent,
            val renderDelegate: RenderDelegate<CustomColumnComponent>
        ) : Child()

        class RememberUpdatedStateChild(
            val component: RememberUpdatedStateComponent,
            val renderDelegate: RenderDelegate<RememberUpdatedStateComponent>
        ) : Child()
    }
}


