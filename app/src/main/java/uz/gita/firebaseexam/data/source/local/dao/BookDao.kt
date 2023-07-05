package uz.gita.firebaseexam.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import uz.gita.firebaseexam.data.source.local.entities.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(bookEntity: BookEntity)

    @Update
    fun updateBook(bookEntity: BookEntity)

    @Query("SELECT * FROM  books")
    fun getBooks(): List<BookEntity>
}