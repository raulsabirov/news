package com.example.news.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.news.R
import com.example.news.databinding.FragmentNewsDetailBinding
import java.lang.ref.WeakReference


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewsDetailFragment  : Fragment(R.layout.fragment_news_detail) {

    private val binding by viewBinding(FragmentNewsDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("NewsDetailFragment onViewCreated" )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("NewsDetailFragment onCreate")
    }

    override fun onStart() {
        super.onStart()
        println("NewsDetailFragment onStart")
    }

    override fun onResume() {
        super.onResume()
        println("NewsDetailFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("NewsDetailFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        println("NewsDetailFragment onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("NewsDetailFragment onDestroy")
    }


    var bytes: WeakReference<ByteArray>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("NewsDetailFragment onAttach" )

        bytes = WeakReference(ByteArray(1000*100*1000))
    }

    override fun onDetach() {
        super.onDetach()
        println("NewsDetailFragment onDetach")

        bytes = null


    }




    companion object{
     //   var bytes: ByteArray? = ByteArray(100*100*1000)




    }


}