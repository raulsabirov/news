package com.example.news.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.BaseFragment
import com.example.news.R
import com.example.news.databinding.FragmentFirstBinding
import com.example.news.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : BaseFragment(R.id.FirstFragment) {


    private val binding by viewBinding(FragmentFirstBinding::bind)

    private val name by lazy {
        arguments?.getString("NAME") ?: ""
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = name
    }
}