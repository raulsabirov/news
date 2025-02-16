package com.example.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news.R

class CustomViewFragment : BaseFragment(R.layout.fragment_custom_view) {

    companion object {
        fun newInstance() = CustomViewFragment()
    }

    private lateinit var viewModel: CustomViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom_view, container, false)
    }

}