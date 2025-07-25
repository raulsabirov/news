package com.example.news.navigation.renderers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.news.navigation.CustomColumnRenderDelegate
import com.example.news.navigation.components.CustomColumnComponent
import com.example.news.presentation.compose.CustomColumnScreen

class CustomColumnRenderer : CustomColumnRenderDelegate {
    
    @Composable
    override fun render(data: CustomColumnComponent) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = { data.onNavigateBack() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Back")
            }
            
            CustomColumnScreen()
        }
    }
}
