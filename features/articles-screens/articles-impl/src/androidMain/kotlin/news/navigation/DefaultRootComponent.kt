package news.navigation

import ru.braveowlet.simple_mvi_example.core.network.KtorWebSocketClient
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import kotlinx.serialization.Serializable
import news.Tabs.DefaultTabsComponent
import news.Tabs.TabsRenderer
import news.navigation.components.DefaultMainComponent
import news.navigation.renderers.MainRenderer


class DefaultRootComponent(
    componentContext: ComponentContext,
    val websocket : KtorWebSocketClient
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ConfigRootChild>()


   //  val websocket = ru.braveowlet.simple_mvi_example.core.network.KtorWebSocketClient( get())


    init {
     //   websocket.connect()

    }


    override fun onBackClicked() {
        TODO("Not yet implemented")
    }

    override val stack = childStack(
        source = navigation,
        serializer = ConfigRootChild.serializer(),
        initialConfiguration = ConfigRootChild.Main,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(config: ConfigRootChild, componentContext: ComponentContext): RootComponent.Child =
        when (config) {

            is ConfigRootChild.Main -> {
                val component = DefaultMainComponent(componentContext, ::navigateToTabs)
                RootComponent.Child.Main(component, MainRenderer())
            }
            
            is ConfigRootChild.Tabs -> {
                val component = DefaultTabsComponent(componentContext, ::navigateBack)
                RootComponent.Child.Tabs(component, TabsRenderer())
            }

        }

    private fun navigateToTabs() {
        navigation.push(ConfigRootChild.Tabs)
    }
    


    private fun navigateBack() {
        navigation.pop()
    }

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }
}
@Serializable
sealed interface ConfigRootChild  {
    @Serializable
    data object Main : ConfigRootChild

    @Serializable
    data object Tabs : ConfigRootChild


}
