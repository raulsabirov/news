package news.navigation.components

import com.arkivanov.decompose.ComponentContext



interface MainComponent {
    fun onNavigateTo()

}

class DefaultMainComponent(
    componentContext: ComponentContext,
    private val onNavigateTo: () -> Unit,
) : MainComponent, ComponentContext by componentContext {
    override fun onNavigateTo() {
        onNavigateTo.invoke()
    }
}
