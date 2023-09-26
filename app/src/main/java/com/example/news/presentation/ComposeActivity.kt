package com.example.news.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.news.R
import com.example.news.presentation.ui.theme.NewsTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(
                count = count,
                onCounterClick = {
                    count.value++
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // ScreenOne()
    HomeScreen(
        count = count,
        onCounterClick = {
            count.value++
        }
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


var count = mutableStateOf(0)

@Composable
fun ScreenOne() {
    Column(
        modifier = Modifier.background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = "Title", fontSize = 32.sp,
            modifier = Modifier.clickable {

            })
        Text(text = "Description", fontSize = 20.sp)
        Image(
            painter = painterResource(id = R.drawable.battery),
            contentDescription = null
        )
    }
}

@Composable
fun HomeScreen(
    count: State<Int>,
    onCounterClick: () -> Unit
) {
    Text(
        text = "Clicks: ${count.value}",
        modifier = Modifier.clickable(onClick = onCounterClick)


    )
}