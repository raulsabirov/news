package com.example.news.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import java.util.Stack

object Solution2 {

}
//commit 1
//commit 2

//dev_2 commit 2


//dev_2 commit 3

//dev_2 commit 4



 val  s: ()-> Unit = { print("") }

class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
}


class Solution {
    fun isSymmetric(root: TreeNode?): Boolean {


        return equal( root, root)


    }

    fun equal(first: TreeNode?, second: TreeNode?): Boolean {
        return first?.`val` == second?.`val` &&
                equal(first?.left, first?.right)
    }
}
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModel()

    val router : Router by inject()
    val navigatorHolder : NavigatorHolder by inject()

    val stack  = Stack<Int>()

    class ListNode(var `val`: Int) {
            var next: ListNode? = null
    }


    val lock = Any()

    override fun onCreate(savedInstanceState: Bundle?) {
        //intersect(intArrayOf(1,2,2,1), intArrayOf(2,2,))

       // merge(intArrayOf(2,0),1, intArrayOf(1),1)
        val openBrackets = listOf('(', '{', '[')
        val pair = mutableMapOf(')' to '(',
            '}' to '{',
            ']' to ']')
        val queue: Queue<String> = LinkedList()

       // println("MainActivity" +buyChoco( listOf(98,54,6,34,66,63,52,39).toIntArray(), 62))

        super.onCreate(savedInstanceState)
        println("MainActivity onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        replaceFragmentOnTop(MainFragment())

       // replaceFragmentOnTop(NewsDetailFragment())




        synchronized(this){

        }

      //  replaceFragments(listOf(MainFragment(), NewsDetailFragment()), true)

       lifecycleScope.launch()
       {
           viewModel.sharedFlow.collect {
               println("collect " + it)

               println("replayCache " + viewModel.sharedFlow.replayCache.last())
               println("replayCache all " + viewModel.sharedFlow.replayCache)
           }


       }

        lifecycleScope.async{

        }

/*       lifecycleScope.launch()
       {
           var result = 0

           val mutex = Mutex()
           // val ms = measureTimeMillis {
           for (i in 1..10000) {
               val job = lifecycleScope.async(Dispatchers.Default) {


                   mutex.withLock {
                       delay(100)
                       return@async result++
                   }
               }

               println(job.await())
           }

       }*/



       // println("measureTimeMillis" + ms)

        val p = PriorityQueue(listOf(5, 3, 6, 1, 2))

        println(p.poll())
        println(p.poll())
        println(p.poll())
        println(p.poll())
        println(p.poll())

        val list = LinkedList<Int>()

    }


    @Synchronized
    fun testFun( i : Int) =
         i+1


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
                replaceFragmentOnTop(NewsDetailFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    fun addFragmentOnTop(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun replaceFragmentOnTop(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
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

        for(f in fList)
             transaction.replace(containerViewId, f)

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }

}