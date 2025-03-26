package com.example.news.presentation.fragments
/*

import android.content.Context
import android.os.Bundle
import android.view.View

import androidx.lifecycle.lifecycleScope
*/
/*import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding*//*

import com.example.news.R
import com.example.news.databinding.FragmentArticlesBinding
import com.example.news.presentation.ArticlesAdapter
import com.example.news.presentation.MainViewModel
import com.example.news.presentation.MyApplication
import javax.inject.Inject


class ArticlesFragment : BaseFragment(R.layout.fragment_articles) {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val binding by viewBinding(FragmentArticlesBinding::bind)

    private val articlesAdapter = ArticlesAdapter()
    private val headerAdapter = HeaderAdapter()

    private val concatAdapter = ConcatAdapter(
        headerAdapter,
        articlesAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticles.adapter = concatAdapter


        headerAdapter.submitList(listOf("HEADER_1", "HEADER_2"))
        mainViewModel.getArticles()
        lifecycleScope.launchWhenStarted {
            mainViewModel.articlesFlow.collect {
                articlesAdapter.submitList(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }


    companion object {
        fun getNewInstance(): ArticlesFragment {
            return ArticlesFragment().apply {
                */
/*           arguments = Bundle().apply {
                putInt(EXTRA_NUMBER, number)
                putLong(EXTRA_TIME, System.currentTimeMillis())
            }*//*

            }
        }
    }
}*/
