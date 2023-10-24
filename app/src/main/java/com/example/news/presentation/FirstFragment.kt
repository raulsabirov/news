package com.example.news.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.BaseFragment
import com.example.news.R
import com.example.news.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : BaseFragment(R.id.FirstFragment) {


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

}