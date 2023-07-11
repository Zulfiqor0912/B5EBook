package uz.gita.B5EBook.presentetion.ui.screens.saved.impl

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.presentetion.ui.screens.saved.SavedUseCase
import javax.inject.Inject

class SavedUseCaseImpl @Inject constructor(private val repository: Repository) : SavedUseCase {
    override fun getSavedBooks(): LiveData<List<BookData>> = repository.getBooksSave()
}