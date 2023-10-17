package com.example.news.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.news.ArticlesFragment
import com.example.news.ArticleDetailFragment
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        // merge(intArrayOf(2,0),1, intArrayOf(1),1)
        val openBrackets = listOf('(', '{', '[')
        val pair = mutableMapOf(
            ')' to '(',
            '}' to '{',
            ']' to ']'
        )
        val queue: Queue<String> = LinkedList()

        val map = mutableMapOf<Char, Int>()

        val hashSet = hashSetOf(1)

        // println("MainActivity" +buyChoco( listOf(98,54,6,34,66,63,52,39).toIntArray(), 62))

        super.onCreate(savedInstanceState)
        println("MainActivity onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.coroutineActivityButton.setOnClickListener {
            Intent(
                this,
                CouroutineActivity::class.java
            ).apply {
                startActivity(this)
            }
        }

        binding.composeActivityButton.setOnClickListener {
            Intent(
                this,
                ComposeActivity::class.java
            ).apply {
                startActivity(this)
            }
        }


        savedInstanceState ?: replaceFragmentOnTop(ArticlesFragment(), ARTICLES)


        // replaceFragmentOnTop(CustomViewFragment())

        //  replaceFragments(listOf(MainFragment(), NewsDetailFragment()), true)

        lifecycleScope.launch()
        {
            viewModel.sharedFlow.collect {
                println("collect " + it)

               println("replayCache " + viewModel.sharedFlow.replayCache.last())
               println("replayCache all " + viewModel.sharedFlow.replayCache)
           }
       }


        val p = PriorityQueue(listOf(5, 3, 6, 1, 2))

        println(p.poll())
        println(p.poll())
        println(p.poll())
        println(p.poll())
        println(p.poll())

        val list = LinkedList<Int>()

        initBottomNavigationBar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                replaceFragmentOnTop(ArticleDetailFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    fun addFragmentOnTop(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun replaceFragmentOnTop(fragment: Fragment, backStackName: String? = null) {
        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.main_container, fragment)
            .addToBackStack(backStackName)
            .commitAllowingStateLoss()


    }

    fun replaceFragments(
        fList: List<Fragment>,
        addToBackStack: Boolean = false,
        containerViewId: Int = R.id.main_container
    ) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        for (f in fList)
            transaction.replace(containerViewId, f)

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }


    fun showArticlesFragment() {

    }

    private fun initBottomNavigationBar() {
        binding.bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.articles -> {
                    replaceFragmentOnTop(ArticlesFragment(), ARTICLES)
                }

                R.id.first -> {

                    supportFragmentManager.saveBackStack(ARTICLES)
                    //  supportFragmentManager.restoreBackStack(FIRST)
                }

                R.id.second -> {
                    supportFragmentManager.restoreBackStack(ARTICLES)
                }
            }
            return@setOnItemSelectedListener true
        }
    }


    fun saveBackStack(oldButton: Int) {
        when (oldButton) {
            R.id.articles -> {
                supportFragmentManager.saveBackStack(ARTICLES)
            }

            R.id.first -> {
                supportFragmentManager.saveBackStack(FIRST)
            }

            R.id.second -> {
                supportFragmentManager.saveBackStack(SECOND)
            }
        }
    }

    var lastBottomButtonClicked = R.id.articles

    companion object {
        const val ARTICLES = "ARTICLES"
        const val FIRST = "FIRST"
        const val SECOND = "SECOND"
    }


    override fun onStart() {
        println("MainActivity onStart")
        super.onStart()
    }

    override fun onResume() {
        println("MainActivity onResume")
        super.onResume()
    }

    override fun onPause() {
        println("MainActivity onPause")
        super.onPause()
    }

    override fun onStop() {
        println("MainActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        println("MainActivity onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        println("MainActivity  onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

}