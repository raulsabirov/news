package com.example.news.DOCS

import android.content.Context
import com.example.news.R
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.LinkedList
import java.util.concurrent.Executors



open class LoggerProvider{
    val  logger = Logger()

    class Logger{
        fun tag(tag: String) = this
        fun d(tag: UserData) = this
    }
}

interface Formater{
    fun phoneFormater(phone :String ) : String
}

private var TAG = "ClassTag"

class UserData(var id: Int, var name: String, var phoneNumber: String)

class UsersHolderSingleton private constructor(
    val formater: Formater,
    val usersRepo: Repository,
    val loggerProvider: LoggerProvider
){
    val users: MutableList<UserData> get() = usersRepo.get().toMutableList()

    fun update(id: Int, newPhone: String) {
        UpdateUseCase(formater, users, loggerProvider)(id, newPhone)
    }

    companion object {
        @Volatile
        private var instance: UsersHolderSingleton? = null

        fun getInstance(formater: Formater, usersRepo: Repository, loggerProvider: LoggerProvider): UsersHolderSingleton {
            return  instance ?: synchronized(this){
                return  instance ?:  UsersHolderSingleton(formater ,usersRepo, loggerProvider)
            }
        }
    }

}

interface Repository {
    fun get(): List<UserData>
}


class UpdateUseCase(val formater : Formater, val users: MutableList<UserData> ,val  loggerProvider : LoggerProvider){

    operator fun invoke(id : Int , newPhone : String){
        //val formattedPhone = context.getString(R.string.description_pattern, newPhone)
        val formattedPhone = formater.phoneFormater(newPhone)
        val copy: (oldUserData: UserData, phone: String) -> UserData = { oldUserData, phoneNumber ->
            UserData(id = oldUserData.id, name = oldUserData.name, phoneNumber = newPhone)
        }

        for (i in 1..users.size) {
            if (users[i].id == id) {
                users[i] = copy(users[i], formattedPhone)
                loggerProvider.logger.tag(TAG).d(users[i])
            }
        }
    }
}