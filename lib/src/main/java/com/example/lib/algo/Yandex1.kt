package com.example.lib.algo

import java.util.LinkedList

/*

Мы хотим вызвать такси своему другу.
Для этого нужно реализовать нечёткий поиск по списку контактов пользователя.

Условия задачи:

Входные данные: строка поиска (input) и список контактов (dataSet).
Нужно найти в списке контактов такие строки, которые максимально похожи на строку поиска.
Похожесть определяется на основе частичного совпадения символов и подстрок (нечёткий поиск).

Примеры:

Контакты: "John Smith", "Mike Marley", "Hillary Cosplay", "Mark Johnson"
Ищем: "Joh"
Результат: ["John Smith", "Mark Johnson"]

Контакты: "John Smith", "Mike Marley", "Hillary Cosplay", "Mark Johnson"
Ищем: "m John"
Результат: ["Mark Johnson"]

Контакты: "John Smith", "Mike Marley", "Hillary Cosplay", "Mark Johnson", "Kamil Englo", "Mjohn Kengsman", "Mjohn Keng"
Ищем: "keng"
Результат: ["Kamil Englo", "Mjohn Kengsman", "Mjohn Keng"]
*/




fun search (names : List<String>, search : String) {
    val searchList = search.split(" ")

/*
    names.filter { name ->
        searchList.forEach {
            if (it in name) return true
            else return false
        }
    }
*/



    fun firstSymbolFinded(names: List<String>, searchList: List<String>) : Boolean {
        names.forEach {
            return (it.first() in searchList.map {  it.first() }.toList())
        }
        return false
    }
}














