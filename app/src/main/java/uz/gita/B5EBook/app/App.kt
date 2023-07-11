package uz.gita.B5EBook.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import uz.gita.B5EBook.data.source.datastorage.MyDataStore
import uz.gita.B5EBook.data.source.local.LocalStorage
import uz.gita.B5EBook.data.source.local.database.BookDatabase
import uz.gita.B5EBook.domain.repositories.impl.RepositoryImpl

@HiltAndroidApp
class App : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var context:Context
    }
    override fun onCreate() {
        super.onCreate()
        context = this
        BookDatabase.init(this)
        MyDataStore.init(this)
        LocalStorage.init(this)
    }
}