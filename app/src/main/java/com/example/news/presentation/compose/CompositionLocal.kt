package com.example.news.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle

val LocalFontStyle = compositionLocalOf { FontStyle.Normal }
// LocalFontStyle - это CompositionLocal ключ.
// Он будет использован нами для записи и чтения значения FontStyle в хранилище.

@Composable
fun LocalFontStyleScreen() {
    Column {
        val italicState = remember { mutableStateOf(false) }
        MyCheckbox("Italic", italicState)

        val fontStyle = if (italicState.value) FontStyle.Italic else FontStyle.Normal
        val localFontStyle = LocalFontStyle.provides(fontStyle)
        CompositionLocalProvider(localFontStyle) {
            MyText(text = "Text 1")
            MyText(text = "Text 2")
            MyText(text = "Text 3")
            MyText(text = "Text 4")
        }
        MyText(text = "Text 5")
    }
}


@Composable
fun MyCheckbox(
    text: String,
    checked: MutableState<Boolean>,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked.value, onCheckedChange = { checked.value = it })
        Text(text = text)
    }
}

/*@Composable
fun MyText(
    text: String
) {
    Text(text = text)
}*/

@Composable
fun MyText(
    text: String,
) {
    Text(text = text, fontStyle = LocalFontStyle.current)
}