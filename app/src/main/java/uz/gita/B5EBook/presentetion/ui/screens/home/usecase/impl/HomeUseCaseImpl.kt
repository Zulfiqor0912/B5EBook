package uz.gita.B5EBook.presentetion.ui.screens.home.usecase.impl

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.presentetion.ui.screens.home.usecase.HomeUseCase
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repositoryImpl: Repository
) : HomeUseCase {
    override fun getAllBooks(): Flow<Result<List<BookData>>> {
        return repositoryImpl.getAllBooks()
    }

    override fun getBooks(): LiveData<List<BookData>> = repositoryImpl.getBooks()
    override fun getBookByQuery(s: String): List<BookData> = repositoryImpl.getAllBooksByQuery(s)


}