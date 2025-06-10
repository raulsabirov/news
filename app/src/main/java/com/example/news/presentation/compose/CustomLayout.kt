package com.example.news.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// https://startandroid.ru/ru/courses/compose/30-course/compose/689-urok-25-custom-layout.html

@Composable
fun MyColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val childConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val placeables = measurables.map {measurable ->
            measurable.measure(childConstraints) // Measure children
        }
        placeables.maxOf { it.width }
        layout(constraints.maxWidth, constraints.maxHeight) { // Decide own size
       // layout(placeables.maxOf { it.width }, placeables.sumOf { it.height }) { // Decide own size
            var y = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = y) // Place children
                y += placeable.height
            }
        }
    }
}

@Preview
@Composable
fun MyColumnPreview() {
    //MyColumn(modifier = Modifier.background(Color.Gray)) {
        MyColumn(modifier = Modifier.height(150.dp).width(100.dp).background(Color.Gray)) {
        Text("Text 1", modifier = Modifier.background(Color.Red))
        Text("Text 2")
        Text("Text 3")
        Text("Text 4")
    }
}