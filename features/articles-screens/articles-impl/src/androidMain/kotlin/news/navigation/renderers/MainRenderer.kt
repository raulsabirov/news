package news.navigation.renderers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import news.navigation.RenderDelegate

import news.navigation.components.MainComponent


class MainRenderer : RenderDelegate<MainComponent> {
    @Composable
    override fun render(component: MainComponent) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {
                component.onNavigateTo()
            }) {

                Text("MainRenderer  onNavigateTo")
            }
        }
    }
}
