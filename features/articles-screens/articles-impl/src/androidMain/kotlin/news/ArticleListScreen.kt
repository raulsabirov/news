package news

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news.MainViewModel
import com.example.news.models.Article
//import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.viewmodel.koinViewModel

val articles =  mutableStateListOf<Article>()

fun addArticle() =
    articles.add(
        Article(
            title = "Article ${articles.size -1}",
            description = "Description for article ${articles.size -1}"
        )
    )

@Composable
fun ArticleListScreen(mainViewModel: MainViewModel = koinViewModel()) {

    val counter = remember { mutableStateOf(1) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                addArticle()

                counter.value++
            }) {
                Text("Add Article")
            }
            if (articles.isNotEmpty()) {
                Button(onClick = {
                    articles.removeAt(0)
                }) {
                    Text("Remove First")
                }
            }
        }

        AnimatedContentList(articles = articles)
    }
}


@Suppress("NonSkippableComposable")
@Composable
fun AnimatedContentList(articles: List<Article>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            items = articles,
            key = { article -> article.id }
        ) { article ->
          AnimatedVisibility(
                visible = true,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                ArticleItem(article = article)
            }

        }

    }
}

@Composable
fun ArticleItem(article: Article) {
    Column{
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            ,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = article.title , style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = article.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = article.title , style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = article.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


@Composable
@Preview
fun ArticleItemPreview(){
    ArticleItem(
        Article(
            title = "Article ${articles.size -1}",
            description = "Description for article ${articles.size -1}"
        )
    )
}