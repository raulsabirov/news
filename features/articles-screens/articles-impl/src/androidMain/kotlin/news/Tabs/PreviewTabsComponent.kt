package news.Tabs

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import news.Articles.ArticleListRenderer
import news.Articles.PreviewArticleListComponent

class PreviewTabsComponent() : TabsComponent {

    override val stack: Value<ChildStack<*, TabsComponent.Child>> =
        MutableValue(
            ChildStack(
                configuration = Unit,
                instance = TabsComponent.Child.ArticlesChild(
                    PreviewArticleListComponent(),
                    ArticleListRenderer()
                )

            )
        )

    override fun onArticlesTabClicked() {
        TODO("Not yet implemented")
    }

    override fun onCustomColumnTabClicked() {
        TODO("Not yet implemented")
    }

    override fun onRememberUpdatedStateTabClicked() {
        TODO("Not yet implemented")
    }

    override fun onBackClicked(toIndex: Int) {
        TODO("Not yet implemented")
    }
}