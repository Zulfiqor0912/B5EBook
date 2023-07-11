package uz.gita.B5EBook.presentetion.ui.screens.desc

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.B5EBook.data.model.BookData
import java.io.File

interface DescUseCase {
    fun downloadFile(context: Context, name: String): Flow<Result<File>>
    fun updateBook(bookData: BookData)

}