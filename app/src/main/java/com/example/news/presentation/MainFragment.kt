package com.example.news

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.databinding.FragmentMainBinding
import com.example.news.presentation.ArticlesAdapter
import com.example.news.presentation.HeaderAdapter
import com.example.news.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModel()
    private val binding by viewBinding(FragmentMainBinding::bind)

    private val articlesAdapter = ArticlesAdapter()
    private val headerAdapter = HeaderAdapter()

    private val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder()
            .setIsolateViewTypes(false)
            .build(),
        headerAdapter,
        articlesAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticles.adapter = concatAdapter


        mainViewModel.getArticles()
        lifecycleScope.launchWhenStarted {
            mainViewModel.articlesFlow.collect {
                articlesAdapter.submitList(it)
            }
        }
    }


    companion object {
        fun getNewInstance(): MainFragment {
            return MainFragment().apply {
                /*           arguments = Bundle().apply {
                putInt(EXTRA_NUMBER, number)
                putLong(EXTRA_TIME, System.currentTimeMillis())
            }*/
            }
        }
    }
}