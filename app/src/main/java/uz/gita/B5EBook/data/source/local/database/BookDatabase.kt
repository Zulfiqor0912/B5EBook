package uz.gita.B5EBook.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.B5EBook.data.source.local.dao.BookDao
import uz.gita.B5EBook.data.source.local.entities.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {
        private lateinit var database: BookDatabase
        private const val NAME_DATABASE = "book_list.db"

        fun init(context: Context) {
            if (!::database.isInitialized) {
                database = Room.databaseBuilder(context, BookDatabase::class.java, NAME_DATABASE)
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun instance() = database
    }
}