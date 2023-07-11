package uz.gita.B5EBook.presentetion.ui.screens.desc.impl

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.presentetion.ui.screens.desc.DescUseCase
import java.io.File
import javax.inject.Inject

class DescUseCaseImpl @Inject constructor(private val repository: Repository) : DescUseCase {
    override fun downloadFile(context: Context, name: String): Flow<Result<File>> {
        return repository.downloadFile(context, name)
    }

    override fun updateBook(bookData: BookData) {
        return repository.updateBook(bookData)
    }
}