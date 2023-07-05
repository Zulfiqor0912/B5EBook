package uz.gita.firebaseexam.presentetion.ui.screens.home.viewModel

import androidx.lifecycle.LiveData
import uz.gita.firebaseexam.data.model.AuthorData
import uz.gita.firebaseexam.data.model.BookData
import uz.gita.firebaseexam.data.model.LastReadBook

interface HomeScreenViewModel {
    val getAllBooksLiveData: LiveData<List<BookData>>
    val getLastBooksLiveData: LiveData<LastReadBook>
    val getAuthorsLiveData: LiveData<List<AuthorData>>
    val clickItem: LiveData<BookData>
    val clickMenuLiveData: LiveData<Unit>
    val clickMoreButton: LiveData<Unit>

    fun clickItemSelected(data: BookData)
    fun getBooks()
    fun getLastReadBooks()
    fun getAuthors()
    fun clickMenu()
    fun clickMoreButton()


//    fun loadBook(url: String)
}