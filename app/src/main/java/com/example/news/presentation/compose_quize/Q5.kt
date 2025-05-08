package com.example.news.presentation.compose_quize

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun Q5() {
    var selected by remember { mutableStateOf("") }

    val items by remember { mutableStateOf(listOf("A", "B", "C")) }


    Column {
        for (item in items) {
            MyItem(item, Modifier.clickable { selected = item })
        }
        Text("Selected = $selected")
    }
}

@Composable
fun MyItem(item: String, modifier: Modifier = Modifier) {
    println(item)
    Text(item, modifier)
}