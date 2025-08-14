package news.navigation

import androidx.core.app.PendingIntentCompat.send
import ru.braveowlet.simple_mvi_example.core.network.KtorWebSocketClient
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import io.ktor.websocket.Frame
import news.navigation.components.DefaultArticleListComponent
import news.navigation.components.DefaultCustomColumnComponent
import news.navigation.components.DefaultRememberUpdatedStateComponent


class DefaultRootComponent(
    componentContext: ComponentContext,
    val websocket : KtorWebSocketClient
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()


   //  val websocket = ru.braveowlet.simple_mvi_example.core.network.KtorWebSocketClient( get())


    init {
     //   websocket.connect()

    }

    override val renderDelegateFactory = RenderDelegateFactory()

    override val stack = childStack(
        source = navigation,
        initialConfiguration = Config.ArticleList,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.ArticleList -> {
                val component = DefaultArticleListComponent(
                    componentContext, 
                    ::navigateToCustomColumn,
                    ::navigateToRememberUpdatedState
                )
                val renderDelegate = renderDelegateFactory.createArticleListDelegate()
                RootComponent.Child.ArticleListChild(component, renderDelegate)
            }
            
            is Config.CustomColumn -> {
                val component = DefaultCustomColumnComponent(componentContext, ::navigateBack)
                val renderDelegate = renderDelegateFactory.createCustomColumnDelegate()
                RootComponent.Child.CustomColumnChild(component, renderDelegate)
            }
            
            is Config.RememberUpdatedState -> {
                val component = DefaultRememberUpdatedStateComponent(componentContext, ::navigateBack)
                val renderDelegate = renderDelegateFactory.createRememberUpdatedStateDelegate()
                RootComponent.Child.RememberUpdatedStateChild(component, renderDelegate)
            }
            
            is Config.Home -> {
                // Для примера используем ArticleList
                val component = DefaultArticleListComponent(
                    componentContext, 
                    ::navigateToCustomColumn,
                    ::navigateToRememberUpdatedState
                )
                val renderDelegate = renderDelegateFactory.createArticleListDelegate()
                RootComponent.Child.ArticleListChild(component, renderDelegate)
            }
        }

    private fun navigateToCustomColumn() {
        navigation.push(Config.CustomColumn)
    }
    
    private fun navigateToRememberUpdatedState() {
        navigation.push(Config.RememberUpdatedState)
    }

    private fun navigateBack() {
        navigation.pop()
    }

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }
}
