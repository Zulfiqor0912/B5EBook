package uz.gita.B5EBook.presentetion.ui.screens.read

import androidx.lifecycle.LiveData
import uz.gita.B5EBook.data.model.BookData

interface ReadViewModel {
    val descScreenLiveData:LiveData<Unit>
    val currentPageLiveData:LiveData<Int>
    fun back()
    fun currentPage(page:Int)

    fun updateBook(data:BookData)
}