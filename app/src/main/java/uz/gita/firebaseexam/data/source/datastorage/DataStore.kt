package uz.gita.firebaseexam.data.source.datastorage

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.gita.firebaseexam.data.model.BookData
import uz.gita.firebaseexam.data.model.LastReadBook

private val AUTHOR_NAME = stringPreferencesKey("book_author_name")
private val BOOK_NAME = stringPreferencesKey("book_name")
private val READED_PAGES = intPreferencesKey("readed_pages_count")
private val TOTAL_PAGES = intPreferencesKey("total_pages_count")

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "storage")

class MyDataStore private constructor(
    private val context: Context
) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var instance: MyDataStore

        fun init(context: Context) {
            instance = MyDataStore(context)
        }

        fun getInstances() = instance

    }

    suspend fun saveLastBookReader(data: BookData, currentPage: Int = 0) {
        context.dataStore.edit {
            it[AUTHOR_NAME] = data.author
            it[BOOK_NAME] = data.title
            it[READED_PAGES] = currentPage
            it[TOTAL_PAGES] = data.page.toInt()
        }
    }

    fun getLastReadedBook(): Flow<LastReadBook> =
        context.dataStore.data.map {
            LastReadBook(
                author = it[AUTHOR_NAME] ?: "",
                title = it[BOOK_NAME] ?: "",
                pages = it[TOTAL_PAGES] ?: 0,
                readedPages = it[READED_PAGES] ?: 0
            )
        }
}