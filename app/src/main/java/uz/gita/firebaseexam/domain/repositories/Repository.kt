package uz.gita.firebaseexam.domain.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.firebaseexam.data.model.AuthorData
import uz.gita.firebaseexam.data.model.BookData
import java.io.File

interface Repository {

    fun downloadBooks(): Flow<Result<List<BookData>>>

    fun downloadBookData(context: Context, data: BookData): Flow<Result<BookData>>

    fun downloadAuthors(): Flow<Result<List<AuthorData>>>

    fun downloadBook(context: Context, title: String): Flow<Result<File>>

}