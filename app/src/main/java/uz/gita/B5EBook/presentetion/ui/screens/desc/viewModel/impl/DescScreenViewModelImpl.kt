package uz.gita.B5EBook.presentetion.ui.screens.desc.viewModel.impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.domain.repositories.impl.RepositoryImpl
import uz.gita.B5EBook.presentetion.ui.screens.desc.viewModel.DescScreenViewModel
import javax.inject.Inject

@HiltViewModel
class DescScreenViewModelImpl @Inject constructor(
    private val repositoryImpl: Repository
) : DescScreenViewModel, ViewModel() {
    override val clickReadLiveData = MutableLiveData<BookData>()
    override val clickBackLiveData = MutableLiveData<Unit>()

    override fun clickReadButton(data: BookData) {
        clickReadLiveData.value = data
    }

    override fun clickBackButton() {
        clickBackLiveData.value = Unit
    }

    override fun clickDownloadButton(context: Context, data: BookData) {
        repositoryImpl.downloadFile(context, data.title)
    }
}