package uz.gita.B5EBook.presentetion.ui.screens.home.usecase.impl

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.B5EBook.data.model.AuthorData
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.impl.RepositoryImpl
import uz.gita.B5EBook.presentetion.ui.screens.home.usecase.HomeUseCase

class HomeUseCaseImpl private constructor(
    private val repositoryImpl: RepositoryImpl = RepositoryImpl.instance()
) : HomeUseCase {

    companion object {
        private lateinit var homeUseCase: HomeUseCaseImpl

        fun instances(): HomeUseCaseImpl {
            if (!::homeUseCase.isInitialized) homeUseCase = HomeUseCaseImpl()
            return homeUseCase
        }
    }

    override fun downloadBooks(): Flow<Result<List<BookData>>> = repositoryImpl.downloadBooks()

    override fun downloadAuthors(): Flow<Result<List<AuthorData>>> =
        repositoryImpl.downloadAuthors()

    override fun downloadBook(context: Context, data: BookData): Flow<Result<BookData>> =
        repositoryImpl.downloadBookData(context, data)


}