package news.RememberUpdatedState

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import news.Tabs.PreviewTabsComponent
import news.Tabs.TabsComponent
import news.Tabs.TabsRenderer
import news.compose.RememberUpdatedStateScreen
import news.navigation.RenderDelegate


class RememberUpdatedStateRenderer : RenderDelegate<RememberUpdatedStateComponent> {
    
    @Composable
    override fun render(data: RememberUpdatedStateComponent) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = { data.onNavigateBack() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Back")
            }
            
            RememberUpdatedStateScreen()
        }
    }
}




@Composable
@Preview
internal fun RememberUpdatedStateRendererPreview() {
    RememberUpdatedStateRenderer().render(PreviewRememberUpdatedStateComponent())
}