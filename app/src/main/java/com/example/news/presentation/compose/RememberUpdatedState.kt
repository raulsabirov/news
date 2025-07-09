package com.example.news.presentation.compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class RememberUpdatedState {
}


@Composable
fun RememberUpdatedStateScreen()
{
    Column {
        var sliderPosition by remember { mutableStateOf(1f) }
        Slider(
            value = sliderPosition,
            valueRange = 1f..10f,
            onValueChange = { sliderPosition = it })

        TrackPosition(position = sliderPosition)
    }

}


@Composable
fun TrackPosition(position: Float) {

    val positionState = rememberUpdatedState(newValue = position)

    LaunchedEffect(key1 = Unit) {
        while(true) {
            delay(1000)
            Log.d("TAG", "track position ${positionState.value}")
        }
    }
}