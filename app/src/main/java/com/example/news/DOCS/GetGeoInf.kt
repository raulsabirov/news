package com.example.news.DOCS

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Date
import java.util.concurrent.atomic.AtomicLong

/*
Основная задача: оптимизировать запросы к сервису fakeGeoIpService
Необходимо реализовать функцию getGeoInfo(ip), которая:

Делает запрос через fakeGeoIpService(ip) — функция, возвращающая произвольные данные об IP адресе
 с рандомной задержкой (нужно реализовать)
Если параллельно пришло несколько запросов на один и тот же IP — только один запрос отправляется,
остальные ждут его результат (параллельных повторных запросов с одним и тем же IP адресом быть не должно)
*/
/*
Дополнительные условия:

Добавить кеширование результатов на основе IP
Кеш должен храниться TTL миллисекунд (например, 500мс)
На повторные запросы на тот же IP в рамках TTL результат должен отдаваться из кеша
Если кеш устарел — повторно обращаемся к фейковому API
Параллельных запросов данных одного и того же IP адреса к фейковому API быть не должно
*/

class GetGeoInfClass(scope: CoroutineScope) {
    val TTL = 500

    val mutex = Mutex()



    val cacheReuest = mutableMapOf<String, String>()
    val cacheTimout = mutableMapOf<String, Long>()

    fun fakeGeoIpService(ip: String): String {
        return ip
    }


    suspend fun getGeoInfo(ip: String): String {



        val cachedIp = cacheReuest[ip]
        if (cachedIp != null  && !isIpCacheTimout(ip))
            return cachedIp

        mutex.withLock {

            val newIp = fakeGeoIpService(ip)
            cacheReuest[ip] =newIp
            cacheTimout[ip] = System.currentTimeMillis()
            return newIp
        }
    }


    fun isIpCacheTimout(ip: String): Boolean {
        val currentTime = System.currentTimeMillis()
        cacheTimout[ip]?.let {
            if (currentTime - it > TTL) return true
        }
        return false
    }
}

fun main (){
    val scope = CoroutineScope(Dispatchers.IO )
    val getGeoInfClass = GetGeoInfClass(scope)


    runBlocking {

        val job1 = scope.launch {
            getGeoInfClass.getGeoInfo("1.1.1.1")
        }


    }


}