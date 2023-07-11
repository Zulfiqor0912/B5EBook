package uz.gita.B5EBook.presentetion.ui.screens.saved.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.presentetion.ui.screens.saved.SavedUseCase
import uz.gita.B5EBook.presentetion.ui.screens.saved.SavedViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModelImpl @Inject constructor(private val useCase: SavedUseCase) : SavedViewModel, ViewModel() {
    override val openHomeScreenLiveData = MutableLiveData<Unit>()
    override val loadBooks = useCase.getSavedBooks()
    override val errorData = MutableLiveData<String>()
    override val clickItemSelected = MutableLiveData<BookData>()

    override fun clickBackButton() {
        openHomeScreenLiveData.value = Unit
    }

    override fun clickItemSelected(data: BookData) {
        clickItemSelected.value = data
    }
}