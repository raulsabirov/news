package news.Tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import news.Articles.ArticleListRenderer
import news.navigation.RenderDelegate


class TabsRenderer : RenderDelegate<TabsComponent> {
    @Composable
    override fun render(component: TabsComponent) {

        Column(modifier = Modifier) {
            Button(onClick = { }) {
                Text("onNavigateTo")
            }
            Children(component = component,
                modifier = Modifier.weight(1F)
                    .consumeWindowInsets(WindowInsets.navigationBars)
            )
            BottomBar(component = component, modifier = Modifier.fillMaxWidth())
        }
    }
}


@Composable
private fun Children(component: TabsComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is TabsComponent.Child.ArticlesChild -> child.renderDelegate.render(  child.component )
          //  is CountersChild -> CountersContent(component = child.component, modifier = Modifier.fillMaxSize())
            is TabsComponent.Child.CustomColumnChild -> child.renderDelegate.render(  child.component )
            is TabsComponent.Child.RememberUpdatedStateChild -> child.renderDelegate.render(  child.component )
        }
    }
}

@Composable
private fun BottomBar(component: TabsComponent, modifier: Modifier = Modifier) {
    val stack by component.stack.subscribeAsState()
    val activeComponent = stack.active.instance

    BottomNavigation(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .navigationBarsPadding(),
        elevation = 0.dp,
    ) {
        BottomNavigationItem(
            selected = activeComponent is TabsComponent.Child.ArticlesChild,
            onClick = component::onArticlesTabClicked,
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Articles",
                )
            },
            label = { Text("Articles",color = MaterialTheme.colorScheme.onPrimary) },
        )

        BottomNavigationItem(
            selected = activeComponent is TabsComponent.Child.CustomColumnChild,
            onClick = component::onCustomColumnTabClicked,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "CustomColumn",
                )
            },
        )

        BottomNavigationItem(
            selected = activeComponent is TabsComponent.Child.RememberUpdatedStateChild,
            onClick = component::onRememberUpdatedStateTabClicked,
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "RememberUpdatedState",
                )
            },
        )
    }
}

@Preview
@Composable
internal fun TabsContentPreview() {
    TabsRenderer().render(PreviewTabsComponent())
}
