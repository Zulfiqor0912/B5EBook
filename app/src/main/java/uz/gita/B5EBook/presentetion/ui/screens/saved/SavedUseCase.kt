package uz.gita.B5EBook.presentetion.ui.screens.saved

import androidx.lifecycle.LiveData
import uz.gita.B5EBook.data.model.BookData


interface SavedUseCase {
    fun getSavedBooks() :LiveData<List<BookData>>
}