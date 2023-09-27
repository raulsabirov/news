package com.example.news.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.news.R
import com.example.news.presentation.ui.theme.NewsTheme


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // ScreenOne()var count = mutableStateOf(0)

}


@Composable
fun ScreenOne(
    checked: State<Boolean>,
    onCheckedChange: (Boolean) -> Unit
) {
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


        Row(verticalAlignment = CenterVertically,
            modifier = Modifier.clickable(onClick = {})
        ) {
            Checkbox(checked = checked.value, onCheckedChange = {})
            Text("Some checkbox text", fontSize = 18.sp)
        }
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    text: State<String>,
    onValueChange: (String) -> Unit
) {
    val textValue = text.value
    OutlinedTextField(value = textValue, onValueChange = onValueChange)
}


class ComposeActivity : ComponentActivity() {
    var count = mutableStateOf(0)
    val text = mutableStateOf("some text")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(
                text = text,
                onValueChange = { newText ->
                    text.value = newText
                }
            )
        }
    }

    override fun onStart() {
        println("ComposeActivity onStart")
        super.onStart()
    }

    override fun onResume() {
        println("ComposeActivity onResume")
        super.onResume()
    }

    override fun onPause() {
        println("ComposeActivity onPause")
        super.onPause()
    }

    override fun onStop() {
        println("ComposeActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        println("ComposeActivity onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        println("ComposeActivity  onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }
}

