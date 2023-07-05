package uz.gita.firebaseexam.app

import android.app.Application
import uz.gita.firebaseexam.data.source.datastorage.MyDataStore
import uz.gita.firebaseexam.data.source.local.database.BookDatabase
import uz.gita.firebaseexam.domain.repositories.impl.RepositoryImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        BookDatabase.init(this)
        MyDataStore.init(this)
        RepositoryImpl.init()
    }
}