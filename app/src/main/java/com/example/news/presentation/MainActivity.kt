package com.example.news.presentation

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news.ArticlesFragment
import com.example.news.ArticleDetailFragment
import com.example.news.BaseFragment
import com.example.news.Navigation
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModels()

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
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigationFlow.collect {
                    onNavigation(it)
                }
            }
        }

        initBottomNavigationBar(savedInstanceState?.getInt(SELECTED_BUTTON) ?: R.id.articles)
    }


    private fun onNavigation(navigation: Navigation) {

        when (navigation) {
            is Navigation.Articles -> {
                replaceFragmentOnTop(ArticlesFragment(), ARTICLES)
            }

            is Navigation.First -> {

                replaceFragmentOnTop(FirstFragment(), FIRST)
            }

            is Navigation.Second -> {

            }

            else -> {}

        }
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt(SELECTED_BUTTON, binding.bottom.selectedItemId)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun initBottomNavigationBar(selected: Int = R.id.articles) {
        binding.bottom.selectedItemId = selected

        binding.bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.articles -> {

                    viewModel.navigate(Navigation.Articles)
                }

                R.id.first -> {
                    viewModel.navigate(Navigation.First)

                    // supportFragmentManager.saveBackStack(ARTICLES)
                    //  supportFragmentManager.restoreBackStack(FIRST)
                }

                R.id.second -> {
                    viewModel.navigate(Navigation.Second)
                    //     supportFragmentManager.restoreBackStack(ARTICLES)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*       return when (item.itemId) {
                   R.id.action_settings -> {
                       replaceFragmentOnTop(ArticleDetailFragment())
                       true
                   }

                   else -> super.onOptionsItemSelected(item)
               }*/
        return true
    }

    fun addFragmentOnTop(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    val stackMap = mutableMapOf<String, Int>()

    fun replaceFragmentOnTop(fragment: BaseFragment, backStackName: String) {

        stackMap[backStackName]?.inc() ?: stackMap.put(backStackName, 0)

        fragment.arguments = Bundle().apply {

        }

        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.main_container, fragment, fragment.tag)
            .addToBackStack(backStackName)
            .commitAllowingStateLoss()
    }

    fun replaceFragments(fList: List<Fragment>, addToBackStack: Boolean = false, containerViewId: Int = R.id.main_container) {
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

    companion object {
        const val ARTICLES = "ARTICLES"
        const val FIRST = "FIRST"
        const val SECOND = "SECOND"

        const val SELECTED_BUTTON = "selected_button"
    }

}