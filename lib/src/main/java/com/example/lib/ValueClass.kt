package com.example.lib


@JvmInline
value class ValueClass(val analitics: Analitics) {
    init {
        //require(analitics  != null)
        print()
    }
    val double: Int
        get() = 1 * 2 // ✅ OK

    //   var invalid: Int = 1 * 2 // ❌ Нельзя — требует field


    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }


    private fun print() {
        println(analitics.name)
    }
}
