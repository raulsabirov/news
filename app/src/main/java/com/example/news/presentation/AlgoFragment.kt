package com.example.news.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news.R
import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class AlgoFragment : Fragment() {

    val queue: Queue<String> = LinkedList(listOf())
    val stack: Stack<Int> = Stack()


    init {
        queue.add("банан")
        queue.add("яблоко")
        queue.add("ананас")
        while (queue.peek() != null) { // или !queue.isEmpty()
            System.out.println(queue.poll());
        }

        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }


        val s = "".substring(1)
    }

    companion object {
        fun newInstance() = AlgoFragment()

    }


    private lateinit var viewModel: AlgoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_algo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlgoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}