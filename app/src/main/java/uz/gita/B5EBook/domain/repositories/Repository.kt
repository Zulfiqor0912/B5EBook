package uz.gita.B5EBook.domain.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.B5EBook.data.model.AuthorData
import uz.gita.B5EBook.data.model.BookData
import java.io.File

interface Repository {

    fun getAllBooks(): Flow<Result<List<BookData>>>

    //    fun downloadBookData(context: Context, data: BookData): Flow<Result<BookData>>
    fun downloadAuthors(): Flow<Result<List<AuthorData>>>
    fun getSaveBooks(): Flow<Result<List<BookData>>>
    fun downloadFile(context: Context, title: String): Flow<Result<File>>


    fun getBooks(): LiveData<List<BookData>>
    fun getBooksSave(): LiveData<List<BookData>>
    fun getBookList(): List<BookData>
    fun getAllBooksByQuery(s: String): List<BookData>
    fun updateBook(book: BookData)

}