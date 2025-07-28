package com.example.microbenchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.news.models.Article
import com.example.news.navigation.components.ArticleListComponent
import com.example.news.navigation.renderers.ArticleListRenderer
import com.example.news.presentation.MainViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleListRendererBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun createMockArticleListComponent() = object : ArticleListComponent {
        override fun onNavigateToCustomColumn() = Unit
        override fun onNavigateToRememberUpdatedState() = Unit
    }

    private fun createMockMainViewModel(articleCount: Int): MainViewModel {
        val viewModel = MainViewModel()
        viewModel.stateArticleList.clear()
        repeat(articleCount) { index ->
            viewModel.stateArticleList.add(
                Article(
                    title = "Benchmark Article $index",
                    description = "Description for benchmark article $index with some longer text to simulate real content"
                )
            )
        }
        return viewModel
    }

    @Test
    fun benchmarkRenderWithSmallList() {
        val renderer = ArticleListRenderer(createMockMainViewModel(10))
        val component = createMockArticleListComponent()

        benchmarkRule.measureRepeated {
            composeTestRule.setContent {
                renderer.render(component)
            }
        }
    }

    @Test
    fun benchmarkRenderWithMediumList() {
        val renderer = ArticleListRenderer(createMockMainViewModel(50))
        val component = createMockArticleListComponent()

        benchmarkRule.measureRepeated {
            composeTestRule.setContent {
                renderer.render(component)
            }
        }
    }

    @Test
    fun benchmarkRenderWithLargeList() {
        val renderer = ArticleListRenderer(createMockMainViewModel(100))
        val component = createMockArticleListComponent()

        benchmarkRule.measureRepeated {
            composeTestRule.setContent {
                renderer.render(component)
            }
        }
    }

    @Test
    fun benchmarkRenderWithEmptyList() {
        val renderer = ArticleListRenderer(createMockMainViewModel(0))
        val component = createMockArticleListComponent()

        benchmarkRule.measureRepeated {
            composeTestRule.setContent {
                renderer.render(component)
            }
        }
    }

    @Test
    fun benchmarkAddArticle() {
        val viewModel = createMockMainViewModel(20)
        val renderer = ArticleListRenderer(viewModel)
        val component = createMockArticleListComponent()

        composeTestRule.setContent {
            renderer.render(component)
        }

        benchmarkRule.measureRepeated {
            runWithTimingDisabled {
                // Подготовка перед измерением
            }
            viewModel.addArticle()
        }
    }

    @Test
    fun benchmarkRemoveArticle() {
        val viewModel = createMockMainViewModel(50)
        val renderer = ArticleListRenderer(viewModel)
        val component = createMockArticleListComponent()

        composeTestRule.setContent {
            renderer.render(component)
        }

        benchmarkRule.measureRepeated {
            runWithTimingDisabled {
                // Убеждаемся, что есть статьи для удаления
                if (viewModel.stateArticleList.isEmpty()) {
                    viewModel.addArticle()
                }
            }
            // Измеряем производительность удаления статьи
            if (viewModel.stateArticleList.isNotEmpty()) {
                viewModel.stateArticleList.removeAt(0)
            }
        }
    }
}
