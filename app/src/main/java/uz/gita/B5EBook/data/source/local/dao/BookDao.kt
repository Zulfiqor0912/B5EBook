package uz.gita.B5EBook.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.data.source.local.entities.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(bookEntity: BookEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBook(bookEntity: BookEntity)

    @Query("SELECT * FROM  books")
    fun getBooks(): LiveData<List<BookData>>

    @Query("SELECT * FROM books WHERE  save = 1")
    fun getBooksSave(): LiveData<List<BookData>>

    @Query("SELECT * FROM books")
    fun getBookList(): List<BookData>

    @Query("SELECT * FROM books WHERE title LIKE :query")
    fun getAllBooksByHomeQuery(query: String): List<BookData>
}