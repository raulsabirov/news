package com.example.lib.algo




// Дано:
// parentWidth – ширина родительского контейнера.
// childSpecs – спецификации ширин дочерних View контейнера, целые числа, причем:
// – если childSpec[i] >= 0, то дочерняя View имеет фиксированную ширину, равную childSpec[i].
// – если childSpec[i] < 0, то это доля, которую займет View от неиспользованной
// ширины parentWidth после расположения в нём всех View с фиксированной шириной.
// Требуется определить финальные размеры всех View.

// Пример: measureWidths(100, listOf(50, -3, -2)) == listOf(50, 30, 20)

fun measureWidths(parentWidth: Int, childSpecs: List<Int>): List<Int> {

    val result = mutableListOf<Int>()

    val positive = childSpecs.filter { it >0}
    val negative = childSpecs.filter { it <0}


    val positiveSum = positive.sum()

    val remnant = parentWidth - positiveSum

    val negativeSum =negative.sum()

    val eachNegativePoint =    remnant / negativeSum



    result.addAll( positive)
    result.addAll( negative.map{ it * eachNegativePoint })

    return result
}


fun main(){
    println( measureWidths(100, listOf(50, -3, -2)))
    println( measureWidths(150, listOf(50, -3, -2)))

}