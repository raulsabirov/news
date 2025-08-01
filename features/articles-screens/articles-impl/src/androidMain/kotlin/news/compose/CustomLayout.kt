package news.compose

import android.util.Log
import androidx.compose.animation.core.copy
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.delay
import kotlin.ranges.coerceIn

// https://startandroid.ru/ru/courses/compose/30-course/compose/689-urok-25-custom-layout.html

@Composable
fun CustomColumn(
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


@Composable
fun CustomColumnScreen() {
    //MyColumn(modifier = Modifier.background(Color.Gray)) {
    val state =remember { mutableStateOf<Int>(1)}
    val redModifier = Modifier.background(Color.Red)
    val grayModifier = Modifier.background(Color.Gray)

    CustomColumn(modifier = Modifier
            .height(150.dp)
            .width(100.dp)
            .background(Color.Gray)) {


       // Text("Text 1", modifier =redModifier.then(Modifier.clickable {  }))
        Text("Text 1", modifier =if (state.value == 1) redModifier else grayModifier)
        Text("Text 2", modifier =if (state.value == 2) redModifier else grayModifier)
        Text("Text 3", modifier =if (state.value == 3) redModifier else grayModifier)
        Text("Text 4", modifier =if (state.value == 4) redModifier else grayModifier)
    }
    val  updState = rememberUpdatedState(1)

    LaunchedEffect(Unit) {
        while(true){
            if(state.value >4)
                state.value =1
            else
                state.value = state.value + 1
            delay(1000)
        }
    }



}

@Preview
@Composable
fun MyColumnScreenPreview() {
    CustomColumnScreen()
}

/*

@Composable
fun MyColumn2(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // Optimization: Remember the measure policy to avoid recreating it on every recomposition
    // if the content lambda hasn't changed.
    val measurePolicy = remember {
        { measurables, constraints ->
            // Optimization: Avoid re-creating childConstraints on every measure pass
            // if the parent constraints haven't changed in a way that affects child constraints.
            // In this specific MyColumn, child constraints are derived simply.
            val childConstraints = constraints.copy(minWidth = 0, minHeight = 0)

            // Measure children
            val placeables = measurables.map { measurable ->
                measurable.measure(childConstraints)
            }

            // Calculate the width and height of the layout.
            // The width should be the maximum width of its children or bounded by parent constraints.
            // The height should be the sum of its children's heights or bounded by parent constraints.
            val width = placeables.maxOfOrNull { it.width }?.coerceIn(constraints.minWidth, constraints.maxWidth) ?: constraints.minWidth
            val height = placeables.sumOf { it.height }.coerceIn(constraints.minHeight, constraints.maxHeight)

            // Log the calculated width and height for debugging/performance monitoring
            Log.d(TAG, "Layout calculated width: $width, height: $height")

            layout(width, height) {
                var yPosition = 0
                placeables.forEach { placeable ->
                    // Place children
                    placeable.placeRelative(x = 0, y = yPosition)
                    yPosition += placeable.height
                }
            }
        }
    }

    Layout(
        content = content,
        modifier = modifier,
        measurePolicy = measurePolicy // Use the remembered measure policy
    )
}
*/
