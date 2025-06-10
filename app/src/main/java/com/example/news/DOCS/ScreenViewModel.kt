package com.example.news.DOCS

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.R

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class ScreenViewModel(
    var configs: ConfigRepository,
    var holder: UserHolder,
    var cards: CardRepository,
    var analytics: AnalyticsService,
    var context: Context,
) : ViewModel() {

    lateinit var config: Config
    lateinit var card: CardType

    var liveData = MutableLiveData<UiModel>()
    var successfulChecks = 0L

    init {
        GlobalScope.launch {
            flowOf(configs.loadConfig())
                .zip(cards.observeAvailableCard()) { it1, it2 -> Pair(it1, it2) }
                .flowOn(Dispatchers.Default)
                .filter {
                    it.second == CardType.PREMIUM && holder.getUserScore() > 42 ||
                            it.second != CardType.PREMIUM ||
                            it.first.specialUsers.contains(holder.getUserId())
                }
                .onEach {
                    config = it.first
                    card = it.second
                }
                .flatMapConcat {
                    flowOf(configs.checkBlackList(it.second, holder.getUserId()))
                        .apply { successfulChecks++ }
                }
                .collect {
                    successfulChecks++

                    liveData.value = UiModel(
                        if (card == CardType.CREDIT) config.creditTitle
                        else if (card == CardType.DEBIT) config.debitTitle
                        else if (card == CardType.KID) config.kidTitle
                        else if (card == CardType.PREMIUM) context.getString(R.string.congratulations)
                        else "",
                        context.getString(
                            R.string.description_pattern,
                            config.kinderSurprizeCashback
                        )
                    )
                }
        }
    }

    //var disposables: CompositeDisposable? = CompositeDisposable()

    override fun onCleared() {
        GlobalScope.launch {
            analytics.sendSuccessfulChecks(successfulChecks)
        }
        super.onCleared()
    }
}

interface Config {
    val debitTitle: String
    val creditTitle: String
    val kidTitle: String
    val kinderSurprizeCashback: Long
    val specialUsers: List<Long>
}

interface ConfigRepository {
    suspend fun loadConfig(): Config

    fun checkBlackList(cardType: CardType, userId: Long): BlackList
}

interface BlackList {
    var successfulChecks: Long
}

interface CardRepository {
    fun observeAvailableCard(): Flow<CardType>
}

interface UserHolder {
    fun getUserId(): Long
    fun getUserScore(): Long
}

interface AnalyticsService {
 //   @FormUrlEncoded
 //   @POST("v1/analytics")
    suspend fun sendSuccessfulChecks(checks: Long)
}

enum class CardType {
    DEBIT, CREDIT, KID, PREMIUM
}

class UiModel(val title: String, val description: String)
