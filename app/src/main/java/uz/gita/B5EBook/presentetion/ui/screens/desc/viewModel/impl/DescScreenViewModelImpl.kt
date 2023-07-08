package uz.gita.B5EBook.presentetion.ui.screens.desc.viewModel.impl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.impl.RepositoryImpl
import uz.gita.B5EBook.presentetion.ui.screens.desc.viewModel.DescScreenViewModel

class DescScreenViewModelImpl : DescScreenViewModel, ViewModel() {
    private val repositoryImpl = RepositoryImpl.instance()
    override val clickReadLiveData = MutableLiveData<BookData>()
    override val clickBackLiveData = MutableLiveData<Unit>()

    override fun clickReadButton(data: BookData) {
        clickReadLiveData.value = data
    }

    override fun clickBackButton() {
        clickBackLiveData.value = Unit
    }

    override fun clickDownloadButton(context: Context, data: BookData) {
        repositoryImpl.downloadBook(context, data.title)
    }
}