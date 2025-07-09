package news

import com.example.news.ArticlesViewModel
//import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewmodelModule = module {
    viewModel { ArticlesViewModel() }

}