package uz.gita.B5EBook.app

import android.app.Application
import uz.gita.B5EBook.data.source.datastorage.MyDataStore
import uz.gita.B5EBook.data.source.local.database.BookDatabase
import uz.gita.B5EBook.domain.repositories.impl.RepositoryImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        BookDatabase.init(this)
        MyDataStore.init(this)
        RepositoryImpl.init()
    }
}