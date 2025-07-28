package com.example.news.DOCS

import android.accounts.Account
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import com.example.news.R
import java.util.LinkedList


/*
object Const {
    val INDEX = 1
}

private var TAG = "ChatSessionController"
open class ChatSessionController(

    private val accountRepository: ChatAccountRepository,
    private val preferencesManager: ChatPreferencesManager, ) {

    lateinit var context: Context

    companion object {
        @Volatile
        private var user: User? = null

    }

    @Synchronized
    fun initChat() {
        val success = false
        val idPrefix = context.getString(R.string.app_name)
        var range = accountRepository.getAccounts().size

        for (i in INDEX..range) {
            val account = accountRepository.getAccounts()[i]

            if (account.isOwner) {
                user = User(
                    id = idPrefix + account.id,
                    name = null,
                    phone = null
                )
            }
        }
        if (success == true) {
            preferencesManager.initPreferences()
            Log.d(TAG, "Init Chat, User = ${user}")
        }

        fun logout() {
            Log.d(TAG, "Logout Chat")
            preferencesManager.getPreferences().edit().clear().apply()
        }
    }


    class User(
        var id: String,
        var name: String? = null,
        var phone: String? = null,
    )
    class Account(
        var id: String,
        var name: String? = null,
        var isOwner: Boolean
    )


    interface ChatAccountRepository {
        fun getAccounts(): LinkedList<Account>

    }

    interface ChatPreferencesManager {
        fun initPreferences()
        fun getPreferences(): SharedPreferences

    }

}
*/
