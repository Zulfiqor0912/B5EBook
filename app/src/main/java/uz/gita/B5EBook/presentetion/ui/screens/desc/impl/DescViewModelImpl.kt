package uz.gita.B5EBook.presentetion.ui.screens.desc.impl

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.data.source.local.LocalStorage
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.presentetion.ui.screens.desc.DescUseCase
import uz.gita.B5EBook.presentetion.ui.screens.desc.DescViewModel
import javax.inject.Inject

@HiltViewModel
class DescViewModelImpl @Inject constructor(
    private val descUseCase: DescUseCase
) : DescViewModel,
    ViewModel() {
    private val localStorage = LocalStorage.getInstance()

    override val clickReadLiveData = MutableLiveData<BookData>()
    override val clickBackLiveData = MutableLiveData<Unit>()
    override val clickSaveLiveData = MutableLiveData<Int>()

    override val check = MutableLiveData<Boolean>()
    override val error = MutableLiveData<String>()
    override val downloadMessage = MutableLiveData<String>()

    override fun clickReadButton(data: BookData) {
        localStorage?.saveLogicRead(true)
        clickReadLiveData.value = data
    }

    override fun clickBackButton() {
        clickBackLiveData.value = Unit
    }

    override fun clickDownloadButton(context: Context, data: BookData) {
        check.value = false
        descUseCase.downloadFile(context, data.reference)
            .onEach { result ->
                result.onSuccess { file ->
                    check.value = true
                    downloadMessage.value = "Download"

                    descUseCase.updateBook(
                        BookData(
                            title = data.title,
                            author = data.author,
                            coverUrl = data.coverUrl,
                            reference = data.reference,
                            genre = data.genre,
                            bookUrl = data.bookUrl,
                            description = data.description
                        )
                    )
                }
                result.onFailure {
                    error.value = it.message.toString()
                }
            }.launchIn(viewModelScope)
    }

    override fun clickSaveButton(data: BookData) {
        clickSaveLiveData.value = data.save.toInt()
        descUseCase.updateBook(data)
    }
}