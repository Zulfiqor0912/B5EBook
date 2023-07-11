package uz.gita.B5EBook.presentetion.ui.screens.home

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.B5EBook.data.model.BookData

interface HomeUseCase {
//    fun downloadBooks(): Flow<Result<List<BookData>>>
//    fun downloadAuthors(): Flow<Result<List<AuthorData>>>
//    fun downloadBook(context: Context, data: BookData): Flow<Result<BookData>>

    fun getAllBooks(): Flow<Result<List<BookData>>>
    fun getBooks(): LiveData<List<BookData>>

    fun getBookByQuery(s:String):List<BookData>
}