package uz.gita.B5EBook.presentetion.ui.screens.home.viewModel.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.B5EBook.data.model.AuthorData
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.data.model.LastReadBook
import uz.gita.B5EBook.data.source.datastorage.MyDataStore
import uz.gita.B5EBook.presentetion.ui.screens.home.usecase.HomeUseCase
import uz.gita.B5EBook.presentetion.ui.screens.home.viewModel.HomeScreenViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModelImpl @Inject constructor(
    private val useCase: HomeUseCase,
//    private val dataStore: MyDataStore = MyDataStore.getInstances()
) : HomeScreenViewModel, ViewModel() {

    override val loadBooks: LiveData<List<BookData>> = useCase.getBooks()
    override val clickItem = MutableLiveData<BookData>()
    override val clickMoreButton = MutableLiveData<Unit>()
    override val searchWordsLiveData = MutableLiveData<List<BookData>>()

    override fun clickItemSelected(data: BookData) {
        clickItem.value = data
    }

    override fun loadBooks() {
        useCase.getAllBooks().onEach {  }.launchIn(viewModelScope)
    }

    override fun clickMoreButton() {
        clickMoreButton.value = Unit
    }

    override fun search(s: String) {
        searchWordsLiveData.value = useCase.getBookByQuery(s)
    }

//    override fun loadData() {
//        Log.d("TTT", "kirdi")
//        repository.downloadBooks().onEach { result ->
//            result.onSuccess { list ->
//                Log.d("TTT", "ichkari")
//                Log.d("BBB", "${list.size}")
//                getAllBooksLiveData.value = list
//            }
//            result.onFailure { error ->
//                Log.d("TTT", "${error.message}")
//            }
//        }.launchIn(viewModelScope)
//    }

//    override fun loadBook(url: String) {
//        repository.downloadFile(url,"")
//    }


}