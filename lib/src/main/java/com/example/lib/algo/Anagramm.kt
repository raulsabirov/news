package com.example.lib.algo



//Например, «heart» и «earth» — анаграммы.
// Строки «python» и «typhon» также являются анаграммами.



fun main(){


    fun isAnagram(str1: String , str2 : String) : Boolean{
        val hash = mutableMapOf<Char, Int> ()



        for (ch in str1){
            hash[ch] = hash.getOrDefault(ch , 0) +1
        }

        for (ch in str2){
            val count  = hash.getOrDefault(ch,0)
            if (count == 0) return false
            hash[ch] = count -1

        }

        return true
    }


    println( isAnagram("A","A"))
    println( isAnagram("ABBA","BBAA"))
    println( isAnagram("chair","arich"))
    println( isAnagram("chair","aiich"))

}