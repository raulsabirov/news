package com.example.shared
actual fun platform(): String = "Desktop ${System.getProperty("os.name")}"
