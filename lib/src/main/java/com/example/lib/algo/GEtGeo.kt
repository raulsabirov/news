package com.example.lib.algo

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

val scope = CoroutineScope(Dispatchers.Default)

val ongoingRequests = mutableMapOf<String, Deferred<String>>()
val cache = mutableMapOf<String, Pair<String, Long>>() // IP -> (результат, время)
val mutex = Mutex()

const val TTL_MS = 500L // TTL кеша

// Фейковый API с задержкой
suspend fun fakeGeoIpService(ip: String): String {
    delay(Random.nextLong(500, 1500))
    println("Fetching from API for $ip")
    return "GeoInfo($ip)"
}

suspend fun getGeoInfo(ip: String): String {
    val now = System.currentTimeMillis()

    mutex.withLock {
        // 1. Проверяем кэш
        val cached = cache[ip]
        if (cached != null && now - cached.second < TTL_MS) {
            println("Returning cached for $ip")
            return cached.first
        }

        // 2. Проверяем, есть ли уже идущий запрос
        val ongoing = ongoingRequests[ip]
/*        if (ongoing != null) {
            return ongoing
        }*/

        // 3. Запускаем новый запрос
        val deferred = scope.async {
            try {
                val result = fakeGeoIpService(ip)
                mutex.withLock {
                    cache[ip] = result to System.currentTimeMillis()
                }
                result
            } finally {
                mutex.withLock {
                    ongoingRequests.remove(ip)
                }
            }
        }

        ongoingRequests[ip] = deferred
        return deferred.await()
    }
}
