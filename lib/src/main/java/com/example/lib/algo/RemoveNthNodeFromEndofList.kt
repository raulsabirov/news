package com.example.lib.algo


/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution1 {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var headCurrent = head
        val map = mutableMapOf<Int, ListNode?>()

        var i = 0
        map[i] = headCurrent

        while (true) {
            headCurrent?.next?.let {
                i++
                map[i] = it
                headCurrent = it.next


            } ?: run {


                val deletedI = i - n
                map[deletedI - 1]?.next = map[deletedI + 1]
                return@removeNthFromEnd map[0]

            }
        }

    }
}