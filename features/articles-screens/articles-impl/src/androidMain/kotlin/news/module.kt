package news


//import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.news.presentation.ArticlesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewmodelModule = module {
    viewModel { ArticlesViewModel() }

}