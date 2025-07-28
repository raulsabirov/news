package com.example.news.presentation

import android.app.AlertDialog
import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.compositionLocalOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news.R
import com.arkivanov.decompose.defaultComponentContext
import com.example.news.navigation.DefaultRootComponent
import com.example.news.navigation.RootContent
import com.example.news.presentation.compose.CustomColumn
import com.example.news.presentation.compose.CustomColumnScreen
import com.example.news.presentation.compose.LocalFontStyleScreen
import com.example.news.presentation.compose.RememberUpdatedStateScreen
import com.example.news.presentation.compose.SubcomposeLayout
//import com.example.news.databinding.ActivityMainBinding
import com.example.news.presentation.fragments.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.LinkedList
import java.util.Queue


interface  ааа {

     val ggg   get () = "1"

}

final data class Model (val test : String)

fun Model.toString() {

}
val LocalFontStyle = compositionLocalOf { FontStyle.FONT_WEIGHT_MAX }

class MainActivity : AppCompatActivity() {

 //   private lateinit var binding: ActivityMainBinding

    val a = null
    val  laz  by lazy{ 1}
//    @Inject
     var mainViewModel= MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
     //   (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        // merge(intArrayOf(2,0),1, intArrayOf(1),1)
        val a : Int?  = 129
        val b : Int?  = 129

        println("a == b")
        println(a == b)
        println(a === b)


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
        val _eventBus = MutableSharedFlow<Unit>(replay = 3)

        println("MainActivity onCreate")

        //System.exit(1)
        setContent {
            //   ComposeScreen(mainViewModel)
          //    ArticleListScreen(mainViewModel)

            // Создаем root component с Decompose навигацией
            val root = DefaultRootComponent(
                componentContext = defaultComponentContext(),
                mainViewModel = mainViewModel
            )

            RootContent(component = root)
        }




        // binding = ActivityMainBinding.inflate(layoutInflater)
        //  setContentView(binding.root)


        /*        binding.coroutineActivityButton.setOnClickListener {
                    Intent(
                        this,
                        CouroutineActivity::class.java
                    ).apply {
                        startActivity(this)
                    }
                }*/

        /*        binding.composeActivityButton.setOnClickListener {
                    Intent(
                        this,
                        ComposeActivity::class.java
                    ).apply {
                        startActivity(this)
                    }
                }

                savedInstanceState ?: replaceFragmentOnTop(ArticlesFragment(), ARTICLES)*/
        // replaceFragmentOnTop(CustomViewFragment(),"")

        //  replaceFragments(listOf(MainFragment(), NewsDetailFragment()), true)


        lifecycleScope.launch(Dispatchers.Default.limitedParallelism(1))
        {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.navigationFlow
                    .onStart { }

                    .onEach { }
                    .collect {

                    }


            }


            val deffered = async {
                ""
            }.await()


        }

        val sharedFlow = flowOf(
            { }, { }, { })//.shareIn(lifecycleScope, SharingStarted.Eagerly)
        /*


                sharedFlow
                    .collect {

                    }
                    .launchIn(lifecycleScope)

        */



        lifecycleScope.launch(Dispatchers.Default.limitedParallelism(1)) {
            sharedFlow
                .collect { it ->
                    it.invoke()
                }
        }

        //    initBottomNavigationBar(savedInstanceState?.getInt(SELECTED_BUTTON) ?: R.id.articles)

        //   val a  =  return 1


        //  val stateflow = StateFlow()


    }




/*    private fun onNavigation(navigation: Navigation) {

        when (navigation) {
            is Navigation.Articles -> {
                replaceFragmentOnTop(ArticlesFragment(), ARTICLES)
            }

            is Navigation.First -> {

                replaceFragmentOnTop(FirstFragment(), FIRST)
            }

            is Navigation.Second -> {
                replaceFragmentOnTop(SecondFragment(), SECOND)
            }

            else -> {}
        }
    }*/

    fun showLoginActivity() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ошибка ")
        builder.setMessage("Необходимо авторизоваться Ошибка Ошибка  Ошибка Ошибка")
        builder.setCancelable(true)
        builder.setPositiveButton(
            android.R.string.ok
        ) { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
     //   outState.putInt(SELECTED_BUTTON, binding.bottom.selectedItemId)
        super.onSaveInstanceState(outState, outPersistentState)
    }


    var oldButton = R.id.articles


/*    private fun initBottomNavigationBar(selected: Int = R.id.articles) {
        binding.bottom.selectedItemId = selected
        oldButton = R.id.articles

        binding.bottom.setOnItemSelectedListener {


            if (oldButton != it.itemId) {
                restoreBackStack(it.itemId)
                saveBackStack(oldButton)
            }

            when (it.itemId) {
                R.id.articles -> {

                    mainViewModel.navigate(Navigation.Articles)
                }

                R.id.first -> {
                    mainViewModel.navigate(Navigation.First)

                    // supportFragmentManager.saveBackStack(ARTICLES)
                    //  supportFragmentManager.restoreBackStack(FIRST)
                }

                R.id.second -> {
                    showLoginActivity()
                    //      mainViewModel.navigate(Navigation.Second)
                    //     supportFragmentManager.restoreBackStack(ARTICLES)
                }
            }

            oldButton = it.itemId
            return@setOnItemSelectedListener true
        }

    }*/

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

    fun restoreBackStack(button: Int) {
        when (button) {
            R.id.articles -> {
                supportFragmentManager.restoreBackStack(ARTICLES)
            }

            R.id.first -> {
                supportFragmentManager.restoreBackStack(FIRST)
            }

            R.id.second -> {
                supportFragmentManager.restoreBackStack(SECOND)
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
