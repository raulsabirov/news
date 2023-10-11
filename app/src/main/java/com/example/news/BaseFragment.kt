package com.example.news

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.news.databinding.FragmentFirstBinding

open class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println(this.javaClass.name + "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println(this.javaClass.name + "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        println(this.javaClass.name + "onDestroyView")
        super.onDestroyView()
    }


    override fun onAttach(activity: Activity) {
        println(this.javaClass.name + "onAttach")
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        println(this.javaClass.name + "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        println(this.javaClass.name + "onStart")
        super.onStart()
    }

    override fun onResume() {
        println(this.javaClass.name + "onResume")
        super.onResume()
    }

    override fun onPause() {
        println(this.javaClass.name + "onPause")
        super.onPause()
    }

    override fun onStop() {
        println(this.javaClass.name + "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {}

}