package uz.gita.B5EBook.presentetion.ui.screens.desc

import android.content.Context
import androidx.lifecycle.LiveData
import uz.gita.B5EBook.data.model.BookData

interface DescViewModel {
    val clickReadLiveData: LiveData<BookData>
    val clickBackLiveData: LiveData<Unit>
    val clickSaveLiveData: LiveData<Int>
    val check: LiveData<Boolean>
    val error: LiveData<String>
    val downloadMessage: LiveData<String>


    fun clickReadButton(data: BookData)
    fun clickBackButton()
    fun clickDownloadButton(context: Context, data: BookData)

    fun clickSaveButton(data: BookData)
}