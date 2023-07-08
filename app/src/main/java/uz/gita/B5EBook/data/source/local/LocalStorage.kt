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




}