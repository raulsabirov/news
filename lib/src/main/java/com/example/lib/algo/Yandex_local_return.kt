package com.example.lib.algo

class Task(var id: Long, val name: String)

val list = listOf(1, 3, 5)
val tasks = HashSet<Task>()

fun main() {
    val task1 = Task(1, "Задача")
    val task2 = Task(1, "Задача")

    tasks.add(task1)
    tasks.add(task2)

   // list.add(7)
    list.forEvery { it ->
        if (it == 3) {
            return@forEvery
        }
        println("$it")
    }

    println("tasks contains ${tasks.size} elements")
    println("Done!")
}

@Synchronized
 inline fun <reified T> List<T>.forEvery( itemAction: (T) -> Unit) {
    reversed().forEach { itemAction(it) }
}