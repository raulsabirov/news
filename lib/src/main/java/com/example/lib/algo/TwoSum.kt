package com.example.lib.algo

class TwoSum {
}

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()

        for (i in nums.indices){
            map[nums[i]] = i
        }

        for ( key1 in map.keys){
            val key2 = target - (key1 ?: continue)

            map[key2]?.let {
                return intArrayOf(map[key1]!!, map[key2]!!)
            }
        }

        return intArrayOf()
    }
}