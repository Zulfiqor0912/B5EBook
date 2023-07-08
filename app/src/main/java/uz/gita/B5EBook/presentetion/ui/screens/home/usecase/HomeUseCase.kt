package uz.gita.B5EBook.presentetion.ui.screens.home.usecase

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.B5EBook.data.model.AuthorData
import uz.gita.B5EBook.data.model.BookData

interface HomeUseCase {
    fun downloadBooks(): Flow<Result<List<BookData>>>
    fun downloadAuthors(): Flow<Result<List<AuthorData>>>
    fun downloadBook(context: Context, data: BookData): Flow<Result<BookData>>
}