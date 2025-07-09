package com.example.news.DOCS

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch







class ProductViewModel(
    private val id: Int,
    private val context : Context

): ViewModel() {

    private val productRepository : ProductRepository? = null
    private val cartRepository : CartRepository? = null
    private val favouriteRepository : FavouriteRepository = object : FavouriteRepository{
        override fun isFavourite(id: Int): Flow<Boolean> {
            TODO("Not yet implemented")
        }
    }

    val product : MutableStateFlow<Product?> = MutableStateFlow<Product?>(null)



    val favouriteButtonText : Flow<String> = favouriteRepository.isFavourite(id).map {
        isFavourite ->

        val resId = if(isFavourite){
            R.string.next
        }else{
            R.string.previous
        }

        context.getString(resId)
    }

    init {

        viewModelScope.launch{
            product.value = productRepository?.getProduct(id)

        }
    }


    suspend fun sendLog(value : String){

    }

}



@Composable
fun ProductScreen(viewmodel : ProductViewModel){
     val context = LocalContext.current

    val scope = rememberCoroutineScope()

    scope.launch {
        viewmodel.sendLog("Hello")
    }

    LaunchedEffect(null){

    }




}

@Composable
private fun ProductImage(
     imageUrl : String,
     modifier : Modifier = Modifier
){

    Box(
        modifier = modifier.fillMaxWidth().height(200.dp)


    )
        {


    }

}







class Product(
    val id : Int,
    val title : String?,
    val description : List<Description>,
    val imageUrl : String,
    val typePictured : Int?

)

class Description(
    var text : String,
    val imageUrl : String
)

interface ProductRepository {
    suspend fun getProduct(id : Int) : Product
}

interface CartRepository {
    suspend fun getInCArtCount(id : Int) : Product
}
interface FavouriteRepository{
    fun isFavourite(id : Int) : Flow<Boolean>
}