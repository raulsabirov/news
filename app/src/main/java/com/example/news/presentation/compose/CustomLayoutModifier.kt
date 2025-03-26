package com.example.news.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
           // .width(50.dp)

    ) {
        Text(text = "Some text and more", fontSize = 20.sp, modifier = Modifier.myLayout())
    }
}

fun Modifier.myLayout() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints) // Measure children
    layout(placeable.width, placeable.height) { // Decide own size
        placeable.placeRelative(0, 0) // Place children
    }


/*  layout(placeable.width + 100, placeable.height + 100) { // Decide own size
        placeable.placeRelative(0, 0) // Place children
    }
    */
}

// constraints - ограничения родителя - Если родитель ограничен в размерах,
//     то эти ограничения распространяются и на детей.

//  placeable - это ответ от дочернего элемента на вопрос родителя: "Какого размера ты планируешь быть?".
//     Он содержит данные о планируемой ширине и высоте дочернего элемента.

// Box получает от myLayout размеры и подстраивается под них. Это происходит с помощью функции layout:



@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}