package com.example.lib.algo

// палиндром
// одинаково читаются слева направо и справа налево
// AABAA  ala  alla


fun main (){

    fun isPalindrom(str:  String): Boolean {
        if(str.length <=1)  return true

        val mid = str.length/2


        for( i  in 0..mid){
            if ( str[i] !=  str[str.length -1 -i]) return false
        }

        return true
    }

    println( isPalindrom("A"))
    println( isPalindrom("ABBA"))
    println( isPalindrom("ABBAA"))

}