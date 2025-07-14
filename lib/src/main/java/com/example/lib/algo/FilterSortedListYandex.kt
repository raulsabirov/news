package com.example.lib.algo


/*
Даны две отсортированных по неубыванию последовательности целых чисел.
Необходимо вернуть все элементы из первой последовательности, которых нет во второй.

Пример:
FilterSortedList([1, 2, 3], [3, 4]) -> [1, 2]*/

interface  EEE{

    val a : String
        get() = " vgrv"


}


fun FilterSortedList(one: List<Int>, two: List<Int>): List<Int> {

    var i = 0
    var j = 0

    val result = mutableListOf<Int>()

    while (true) {

        if (one[i] == two[j]) {
            i++; j++;
        }

        if (one[i] < two[j]) {
            result.add(one[i]);  i++;
        }

        if (one[i] > two[j]) {
            for( k in  i until one.size)
                result.add(one[k])

            break
        }



        if (i == one.size - 1) break

    }

    return result
}


fun main() {

    println(FilterSortedList(listOf(1, 2, 3), listOf(3, 4)))


    println(FilterSortedList(listOf(1, 2, 3,6,10), listOf(3, 4,5,7)))

}
