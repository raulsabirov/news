package com.example.lib.algo




enum class NOMINAL(val nom : Int) {

    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIVTEEN(50),
}



    fun get(count : Int) : Map<NOMINAL, Int>? {

        if(count == 0)      return null

        val resultMap = mutableMapOf<NOMINAL, Int>()

        val nominalSorted = NOMINAL.values()
        nominalSorted.sortDescending()

        var mutableCount = count

        nominalSorted.forEach{  it->
          val currentNominal  =  it.nom

          val first  = mutableCount / currentNominal    // 125 /50   = 2
                                                        // 25 /50 = 0
                                                        // 25/20  =1
          if( first > 0)  {
              resultMap[it] = first
              mutableCount = mutableCount - first * currentNominal
          }
            else  return@forEach



        }

        return resultMap
    }

fun main(){

    println(get(125))
    println(get(1025))
}