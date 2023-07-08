package uz.gita.B5EBook.presentetion.ui.screens.home.viewModel

import androidx.lifecycle.LiveData
import uz.gita.B5EBook.data.model.AuthorData
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.data.model.LastReadBook

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