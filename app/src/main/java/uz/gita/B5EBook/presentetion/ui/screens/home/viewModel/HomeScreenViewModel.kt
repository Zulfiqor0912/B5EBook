package uz.gita.B5EBook.presentetion.ui.screens.home.viewModel

import androidx.lifecycle.LiveData
import uz.gita.B5EBook.data.model.AuthorData
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.data.model.LastReadBook

interface HomeScreenViewModel {
    val loadBooks: LiveData<List<BookData>>
    val clickItem: LiveData<BookData>
    val clickMoreButton: LiveData<Unit>
    val searchWordsLiveData: LiveData<List<BookData>>

    fun clickItemSelected(data: BookData)
    fun loadBooks()
    fun clickMoreButton()

    fun search(s: String)


//    fun loadBook(url: String)
}