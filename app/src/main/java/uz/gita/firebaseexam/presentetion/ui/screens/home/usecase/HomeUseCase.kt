package uz.gita.firebaseexam.presentetion.ui.screens.home.usecase

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.firebaseexam.data.model.AuthorData
import uz.gita.firebaseexam.data.model.BookData

interface HomeUseCase {
    fun downloadBooks(): Flow<Result<List<BookData>>>
    fun downloadAuthors(): Flow<Result<List<AuthorData>>>
    fun downloadBook(context: Context, data: BookData): Flow<Result<BookData>>
}