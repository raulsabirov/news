package news.Tabs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import news.Articles.ArticleListRenderer
import news.Articles.DefaultArticleListComponent
import news.RememberUpdatedState.DefaultRememberUpdatedStateComponent
import news.RememberUpdatedState.RememberUpdatedStateRenderer
import news.navigation.components.DefaultCustomColumnComponent
import news.navigation.renderers.CustomColumnRenderer


internal class DefaultTabsComponent(
    componentContext: ComponentContext,
    private val onNavigateTo: () -> Unit,
    private val onDynamicFeaturesItemSelected: () -> Unit = {} ,
    private val onCustomNavigationItemSelected: () -> Unit = {},
    private val onPagesItemSelected: () -> Unit = {},
    private val onSharedTransitionsItemSelected: () -> Unit = {},
) : TabsComponent, ComponentContext by componentContext {

    private val nav = StackNavigation<ConfigTabs>()

    private val _stack: Value<ChildStack<ConfigTabs, TabsComponent.Child>> =
        childStack(
            source = nav,
            serializer = ConfigTabs.serializer(),
            initialConfiguration = ConfigTabs.ArticleList,
            childFactory = ::child,
        )

    override val stack: Value<ChildStack<*, TabsComponent.Child>> = _stack


    private fun child(config: ConfigTabs, componentContext: ComponentContext): TabsComponent.Child =
        when (config) {
            is ConfigTabs.ArticleList -> {
                val component = DefaultArticleListComponent(
                    componentContext,
                    ::onCustomColumnTabClicked,
                    ::onRememberUpdatedStateTabClicked
                )

                TabsComponent.Child.ArticlesChild(component, ArticleListRenderer())
            }

            is ConfigTabs.CustomColumn -> {
                val component = DefaultCustomColumnComponent(componentContext, ::navigateBack)
                TabsComponent.Child.CustomColumnChild(component, CustomColumnRenderer())

            }

            is ConfigTabs.RememberUpdatedState -> {
                val component = DefaultRememberUpdatedStateComponent(componentContext, ::navigateBack)
                TabsComponent.Child.RememberUpdatedStateChild(component, RememberUpdatedStateRenderer())
            }

 /*           is ConfigTabs.Home -> {
                // Для примера используем ArticleList
                val component = DefaultArticleListComponent(
                    componentContext,
                    ::navigateToCustomColumn,
                    ::navigateToRememberUpdatedState
                )
                val renderDelegate = renderDelegateFactory.createArticleListDelegate()
                TabsComponent.Child.ArticlesChild(component, renderDelegate)
            }*/
        }


    override fun onArticlesTabClicked() {
        nav.bringToFront(ConfigTabs.ArticleList)
    }

    override fun onCustomColumnTabClicked() {
        nav.bringToFront(ConfigTabs.CustomColumn)
    }

    override fun onRememberUpdatedStateTabClicked() {
        nav.bringToFront(ConfigTabs.RememberUpdatedState)
    }

    private fun navigateBack() {
        nav.pop()
    }

    override fun onBackClicked(toIndex: Int) {
        nav.popTo(index = toIndex)
    }
}
