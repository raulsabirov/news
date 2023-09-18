package com.example.news.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.R
import com.example.news.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel : MainViewModel by viewModel()
    private val binding  by viewBinding (FragmentMainBinding::bind )

    private lateinit var adapter : ArticlesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("MainFragment onViewCreated" )

        adapter = ArticlesAdapter()

        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArticles.adapter = adapter

        lifecycleScope.launchWhenStarted {
            mainViewModel.articlesFlow().collect{
                adapter.setItems(it)
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("MainFragment onDestroyView" )
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("MainFragment onAttach" )
       // context.applicationContext.
    }

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         println("MainFragment onCreate" )
     }
     override fun onStart (){
         super.onStart()
         println("MainFragment onStart" )
     }
     override fun onResume (){
         super.onResume()
         println("MainFragment onResume" )
     }
     override fun onPause (){
         super.onPause()
         println("MainFragment onPause" )
     }
     override fun onStop (){
         super.onStop()
         println("MainFragment onStop" )
     }
     override fun onDestroy (){
         super.onDestroy()
         println("MainFragment onDestroy" )
     }
     override fun onDetach (){
         super.onDetach()
         println("MainFragment onDetach" )
     }

     override fun onSaveInstanceState(outState: Bundle) {}



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