package uz.gita.B5EBook.presentetion.ui.screens.home

import androidx.lifecycle.LiveData
import uz.gita.B5EBook.data.model.BookData

interface HomeViewModel {
    val loadBooks: LiveData<List<BookData>>
    val clickItem: LiveData<BookData>
    val clickMoreButton: LiveData<Unit>
    val searchWordsLiveData: LiveData<List<BookData>>
    val finishLiveData: LiveData<Boolean>

    fun clickItemSelected(data: BookData)
    fun loadBooks()
    fun clickMoreButton()

    fun search(s: String)

    fun finish(boolean: Boolean)


//    fun loadBook(url: String)
}