package com.example.news.presentation.fragments
/*

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.R
import com.example.news.databinding.FragmentFirstBinding
import com.example.news.presentation.MainViewModel

*/
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 *//*

class FirstFragment : BaseFragment(R.layout.fragment_first) {
    val viewModel: MainViewModel by activityViewModels()

    private val binding by viewBinding(FragmentFirstBinding::bind)

    private val name by lazy {
        arguments?.getString("NAME") ?: " FirstFragment "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        count++
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        count--
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textviewFirst.text = name + "  $count"
    }

    companion object {
        var count = 0
    }

}*/
