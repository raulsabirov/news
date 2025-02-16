package com.example.news.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    val myTag = this.javaClass.getSimpleName()

    var numOfFragments = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("________" + this.javaClass.getSimpleName() + " onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("________" + this.javaClass.getSimpleName() + " onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        println("________" + this.javaClass.getSimpleName() + " onDestroyView")
        super.onDestroyView()
    }


    override fun onAttach(activity: Activity) {
        println("________" + this.javaClass.getSimpleName() + " onAttach")
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        println("________" + this.javaClass.getSimpleName() + " onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        println("________" + this.javaClass.getSimpleName() + " onStart")
        super.onStart()
    }

    override fun onResume() {
        println("________" + this.javaClass.getSimpleName() + " onResume")
        super.onResume()
    }

    override fun onPause() {
        println("________" + this.javaClass.getSimpleName() + " onPause")
        super.onPause()
    }

    override fun onStop() {
        println("________" + this.javaClass.getSimpleName() + " onStop")
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        println("________" + this.javaClass.getSimpleName() + " onDetach")
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {}

}