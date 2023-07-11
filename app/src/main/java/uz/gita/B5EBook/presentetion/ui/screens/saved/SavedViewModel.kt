package uz.gita.B5EBook.presentetion.ui.screens.saved

import androidx.lifecycle.LiveData
import uz.gita.B5EBook.data.model.BookData

interface SavedViewModel {
    val openHomeScreenLiveData: LiveData<Unit>
    val loadBooks: LiveData<List<BookData>>
    val errorData: LiveData<String>
    val clickItemSelected:LiveData<BookData>
    fun clickBackButton()
    fun clickItemSelected(data:BookData)

}