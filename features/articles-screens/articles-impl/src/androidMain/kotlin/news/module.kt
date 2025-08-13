package news


//import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.news.presentation.ArticlesViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.braveowlet.simple_mvi_example.core.network.KtorWebSocketClient
import ru.braveowlet.simple_mvi_example.core.network.createKtorClient
import ru.braveowlet.simple_mvi_example.core.network.networkModule


val viewmodelModule = module {
    viewModel { ArticlesViewModel() }

    single<HttpClient> { createKtorClient() }

    single<KtorWebSocketClient> { KtorWebSocketClient(get())  }
  //  networkModule
}

