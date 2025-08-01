package news.navigation.renderers

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import news.navigation.ArticleListRenderDelegate
import news.navigation.components.ArticleListComponent
import com.example.news.presentation.ArticlesViewModel
import news.AnimatedContentList
import news.articles

class ArticleListRenderer(
    private val mainViewModel: ArticlesViewModel? = null
) : ArticleListRenderDelegate {
    
    @Composable
    override fun render(data: ArticleListComponent) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    mainViewModel?.addArticle()
                }) {
                    Text("Add Article")
                }
                
                Button(onClick = { 
                    data.onNavigateToCustomColumn() 
                }) {
                    Text("Custom Column")
                }
                
                Button(onClick = { 
                    data.onNavigateToRememberUpdatedState() 
                }) {
                    Text("Remember Updated State")
                }
                
                if (articles.isNotEmpty()) {
                    Button(onClick = {
                        articles.removeAt(0)
                    }) {
                        Text("Remove First")
                    }
                }
            }


            val state = rememberLazyListState ()
            AnimatedContentList(articlesList = articles, listState =state)
        }
    }
}
