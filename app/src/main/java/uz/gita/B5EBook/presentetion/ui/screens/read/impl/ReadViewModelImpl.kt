package uz.gita.B5EBook.presentetion.ui.screens.read.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.presentetion.ui.screens.read.ReadUseCase
import uz.gita.B5EBook.presentetion.ui.screens.read.ReadViewModel
import javax.inject.Inject

@HiltViewModel
class ReadViewModelImpl @Inject constructor(private val useCase: ReadUseCase) : ReadViewModel, ViewModel() {
    override val descScreenLiveData = MutableLiveData<Unit>()
    override val currentPageLiveData = MutableLiveData<Int>()

    override fun back() {
        descScreenLiveData.value = Unit
    }

    override fun currentPage(page: Int) {
        currentPageLiveData.value = page
    }

    override fun updateBook(data: BookData) {
        useCase.updateBook(data)
    }
}