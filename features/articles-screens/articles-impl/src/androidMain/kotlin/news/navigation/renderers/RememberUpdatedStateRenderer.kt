package news.navigation.renderers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import news.compose.RememberUpdatedStateScreen
import news.navigation.RememberUpdatedStateRenderDelegate
import news.navigation.components.RememberUpdatedStateComponent


class RememberUpdatedStateRenderer : RememberUpdatedStateRenderDelegate {
    
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
