package com.example.news

import platform.Foundation.NSUUID

actual fun generateUUID(): String {
    return NSUUID().UUIDString()
}