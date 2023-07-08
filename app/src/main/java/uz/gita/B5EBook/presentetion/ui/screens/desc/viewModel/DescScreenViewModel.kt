package uz.gita.B5EBook.presentetion.ui.screens.desc.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import uz.gita.B5EBook.data.model.BookData

interface DescScreenViewModel {
    val clickReadLiveData: LiveData<BookData>
    val clickBackLiveData: LiveData<Unit>


    fun clickReadButton(data: BookData)
    fun clickBackButton()
    fun clickDownloadButton(context: Context,data: BookData)
}