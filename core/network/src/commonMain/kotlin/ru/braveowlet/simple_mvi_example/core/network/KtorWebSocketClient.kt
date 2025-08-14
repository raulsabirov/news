package ru.braveowlet.simple_mvi_example.core.network

import io.ktor.client.*

import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow


class KtorWebSocketClient(private val httpClient: HttpClient) {

    val receivedMessages = MutableStateFlow("")

    fun connect() {
        CoroutineScope(Dispatchers.IO).launch {
            httpClient.webSocket("wss://demo.piesocket.com/v3/channel_123?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self") { // 10.0.2.2 для localhost в эмуляторе
                send("Привет, сервер!")

                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val text = frame.readText()
                        println("Получено сообщение: $text")
                        receivedMessages.value = text
                    }
                }
            }
        }
    }

    fun close() {
        httpClient.close()
    }
}
