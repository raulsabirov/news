package com.example.news.presentation

import android.content.res.Resources.Theme
import android.util.ArrayMap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.news.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

val stateF = MutableStateFlow(1)


@Composable
fun ComposeScreen(mainViewModel: MainViewModel? = null) {
    var (count, setCount) = remember { mutableStateOf(0) }

    var count2 by remember { mutableStateOf(0) }

    LocalContext.current
    Column {
        Button(onClick = {
            setCount(count + 1)
            setCount(count + 1)
        }
        ) {
            Text("setCount")
        }

        Button(modifier = Modifier.width(100.dp),

            onClick = {
                count2 = count2 + 1
                count2 = count2 + 1
            }
        ) {
            Text("count2" + count2 )

        }
    }

    val state0 = mainViewModel?.stateResponse?.collectAsState()
    val state = remember { mutableStateOf(0) }
    val test = remember { 0 }
    // val arratMap =    ArrayMap(1,1)
    val r = rememberSaveable() { 1 }


    isSystemInDarkTheme()
    DisposableEffect(null) {
        onDispose {

        }
    }
   // produceState() { }

    val scope = rememberCoroutineScope()

    LaunchedEffect(null) {

        mainViewModel?.stateResponse
            ?.collect {

            }

        //   mainViewModel.stateRequest.value = "2"
    }

    //  stateF.collectAsStateWithLifecycle


    // Snackbar()
    val nul = null

    val nu2 = listOf(1,2,"",true)

    LocalContext.current
    // центрировать элементы по экрану
    // пиксель перфект

    // flow или stateflow при повороте экран

    // тип result
    Column {
/*
        val count by produceState(initialValue = 0) {
            while (true) {
                delay(1000)
                value++
            }
        }
*/

        MainScreen()
        /*
                MySlider(
                    content2 =
                    { Foo() }
                        //TrackPosition(1.0f)

                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue)
                        .size(100.dp)
                        .padding(10.dp)
                        .background(Color.Red)
                )

                Row(){

                    Box(modifier = Modifier.weight(1.0f))
                    {
                        Text(  modifier = Modifier
                            .fillMaxWidth(),
                            text = "1111")
                    }
                    Box(  modifier = Modifier
                        .weight(1.0f)
                        .padding(bottom = 20.dp))
                    {
                        Text(  modifier = Modifier
                            .fillMaxWidth(),
                            text = "22222")
                    }

                }



                Text(  modifier = Modifier
                    .horizontalScroll(rememberScrollState()),
                   text = "4444445555555555666666666777777778888888899999")

                Button(  onClick = { } ) { Text(" 1") }

                Button(   onClick = { }  ) {  Text("2") }

                Button(  onClick = { } ) {  Text("3")  }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .background(Color.Cyan)
                ) {
                    Column(Modifier.fillMaxHeight()) {
                        Text("33333333")
                        Text("33333333")
                        Text("33333333")
                    }

                    Column(
                        Modifier
                            .fillMaxHeight()
                            .offset()
                            .onSizeChanged { }) {
                        Text("44444444444444")
                        Text("44444444444444")
                    }
                }


                Foo()
                FooBy()


                RecompositionExample()

                SideEffect {

                }*/

    }

    fun Modifier.myLayout() = layout { measurable, constraints ->
        val placeable = measurable.measure(constraints) // Measure children
        layout(placeable.width, placeable.height) { // Decide own size
            placeable.placeRelative(0, 0) // Place children
        }
    }
}

@Composable
fun MySlider(
    content: (@Composable () -> Unit)? = null,
    content2: @Composable () -> Unit,

    callback: () -> String = { "" }

) {
    Column {
        var sliderPosition by remember { mutableStateOf(5f) }

        val result = callback()

        Image(
            //  modifier = Modifier.alpha {  1},
            painter = painterResource(id = R.drawable.battery),
            contentDescription = ""
        )
        Slider(
            modifier = Modifier
                .onSizeChanged { }
                .clickable { },


            value = sliderPosition,
            valueRange = 1f..10f,
            onValueChange = { sliderPosition = it })

        //   TrackPosition(position = sliderPosition)
    }

}

@Composable
fun TrackPosition(position: Float) {

    val positionState = rememberUpdatedState(newValue = position)

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(1000)
            //      Log.d("TAG", "track position ${positionState.value}")
        }
    }
}

@Composable
fun Foo() {
    val text: MutableState<String> = remember { mutableStateOf("") }
    println("Foo")
    Button(onClick = { text.value = "${text.value} * " }) {
        Text("Foo-")
        Text(text.value)
    }
}


@Composable
fun FooBy() {
    var text by remember { mutableStateOf("") }
    println("FooBy")

    Button(onClick = { text = "$text *" }) {
        Text("FooBy-")
        Text(text)
    }
}


@Composable
fun RecompositionExample() {
    /* Счётчик для обновления состояния. Вернёмся к разбору remember позже */
    var count by remember {
        mutableStateOf(0)
    }

    Column {
        ButtonCountWidget { count++ }
        TitleWidget(count.toString())
    }
}

/** Кнопка для обновления стейта для проведения теста */
@Composable
fun ButtonCountWidget(onClickAction: () -> Unit) {
    Button(onClick = onClickAction) {
        Text("Click on me")
    }
}

/** Виджет с текстом */
@Composable
fun TitleWidget(title: String) {
    Text(text = title)
}

@Preview
@Composable
fun MyComposablePreview() {
    ComposeScreen()
}

@Composable
fun MainScreen() {
    SideEffect { println(" clickcounter - 0") }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer { },
        color = MaterialTheme.colorScheme.background
    ) {
        SideEffect { println(" clickcounter - 1") }
        var clicks by remember { mutableStateOf(0) }
        ClickCounter(
            clicks = clicks,
            onCLick = { clicks += 1 }
        )

    }
}

@Composable
fun ClickCounter(clicks: Int, onCLick: () -> Unit) {
    Button(onClick = onCLick) {
        SideEffect { println(" clickcounter - 1") }
        Text("i've been clicked $clicks times")
    }

}


@Composable
fun MyBox() {
    Box {
        var imageHeightPx by remember { mutableStateOf(0) }
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "I'm above the text",
            modifier  = Modifier
                    . fillMaxWidth ()
                .onSizeChanged { size -> // Don't do this
                    imageHeightPx = size.height
                }
        )
        Text(
            text = "I'm below the image",
            modifier = Modifier.padding(
                top = with(LocalDensity.current) { imageHeightPx.toDp() }
            )
        )
    }
}