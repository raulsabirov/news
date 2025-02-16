package com.example.news.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.R
import com.example.news.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : BaseFragment(R.id.SecondFragment) {

    private val binding by viewBinding(FragmentSecondBinding::bind)

    private val name by lazy {
        arguments?.getString("NAME") ?: " SecondFragment "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FirstFragment.count++
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        FirstFragment.count--
        super.onDestroy()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textviewSecond.text = name + "  ${FirstFragment.count}"
    }


    companion object {
        var count = 0
    }
}