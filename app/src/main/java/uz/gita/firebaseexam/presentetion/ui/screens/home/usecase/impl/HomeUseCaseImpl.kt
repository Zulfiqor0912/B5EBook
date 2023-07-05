package uz.gita.firebaseexam.presentetion.ui.screens.home.usecase.impl

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.firebaseexam.data.model.AuthorData
import uz.gita.firebaseexam.data.model.BookData
import uz.gita.firebaseexam.domain.repositories.impl.RepositoryImpl
import uz.gita.firebaseexam.presentetion.ui.screens.home.usecase.HomeUseCase

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