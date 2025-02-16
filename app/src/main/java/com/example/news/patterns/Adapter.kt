package com.example.news.patterns

// The Adapter design pattern allows the interface of an existing class to be used as another interface. It is often used to make existing classes work with others without modifying their source code.

// In Kotlin, we can implement the Adapter pattern using either class-based or object-based adapters.

// Printer is the target interface that the client expects.
// ModernPrinter is the class to be adapted (Adaptee).
// ModernPrinterAdapter is the class-based adapter that adapts the ModernPrinter to the Printer interface.


// Target interface that the client expects
interface Printer {
    fun print()
}

// Adaptee (the class to be adapted)
class ModernPrinter {
    fun startPrint() {
        println("Printing in a modern way")
    }
}

// Class-based Adapter
class ModernPrinterAdapter(private val modernPrinter: ModernPrinter) : Printer {
    override fun print() {
        modernPrinter.startPrint()
    }
}

// Client code
fun main() {
    val modernPrinter = ModernPrinter()
    val legacyPrinter: Printer = ModernPrinterAdapter(modernPrinter)

    legacyPrinter.print()
}