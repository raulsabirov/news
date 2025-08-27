package news.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import news.Tabs.TabsComponent
import news.Tabs.TabsRenderer
import news.navigation.components.MainComponent
import news.navigation.renderers.MainRenderer

interface RootComponent : BackHandlerOwner {
    val stack: Value<ChildStack<*, Child>>

    
    fun onBackClicked()
    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class Main(
            val component: MainComponent,
            val renderDelegate: MainRenderer
        ) : Child()

        class Tabs(
            val component: TabsComponent,
            val renderDelegate: TabsRenderer
        ) : Child()
    }
}
