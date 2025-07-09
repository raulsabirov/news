package com.example.news

actual fun generateUUID(): String {
    return java.util.UUID.randomUUID().toString()
}