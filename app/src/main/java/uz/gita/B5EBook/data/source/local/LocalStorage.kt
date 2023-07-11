package uz.gita.B5EBook.data.source.local

import android.content.Context
import android.content.SharedPreferences

class LocalStorage(context: Context) {

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null


    init {
        preferences = context.getSharedPreferences("EBOOK", Context.MODE_PRIVATE)
        editor = preferences?.edit()
    }


    companion object {
        private var localStorage: LocalStorage? = null

        fun getInstance(): LocalStorage? {
            return localStorage
        }

        fun init(context: Context) {
            if (localStorage == null) localStorage = LocalStorage(context)
        }

    }


    fun saveFirstApp(bool: Boolean) {
        editor?.putBoolean("APP", bool)?.apply()
    }

    fun getFirstApp(): Boolean? = preferences?.getBoolean("APP", true)

    fun saveLogicRead(bool: Boolean) {
        editor?.putBoolean("READ", bool)
        editor?.apply()
    }

    fun getLogicRead() = preferences?.getBoolean("READ", true)

    fun saveBookPage(name: String, page: Int) {
        editor?.putInt(name, page)?.apply()
    }

    fun getBookPage(name: String): Int? = preferences?.getInt(name, 0)

    fun saveBookAuthor(author: String) {
        editor?.putString("AUTHOR", author)?.apply()
    }

    fun getAuthor(): String? = preferences?.getString("AUTHOR", "")

    fun saveBookName(name: String) {
        editor?.putString("TITLE", name)?.apply()
    }

    fun getBookName(): String? = preferences?.getString("TITLE", "")


    fun saveBookImage(imageUrl: String) {
        editor?.putString("IMGUrl", imageUrl)?.apply()
    }

    fun getBookImage() = preferences?.getString("IMGUrl", "")

    fun saveReference(reference: String) {
        editor?.putString("REF", reference)?.apply()
    }
    fun getReference() = preferences?.getString("REF","")
}