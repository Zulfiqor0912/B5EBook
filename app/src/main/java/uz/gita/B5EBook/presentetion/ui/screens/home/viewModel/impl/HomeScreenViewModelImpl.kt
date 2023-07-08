package uz.gita.B5EBook.presentetion.ui.screens.home.viewModel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import uz.gita.B5EBook.presentetion.ui.screens.home.usecase.impl.HomeUseCaseImpl
import uz.gita.B5EBook.presentetion.ui.screens.home.viewModel.HomeScreenViewModel

class HomeScreenViewModelImpl(
    private val useCase: HomeUseCase = HomeUseCaseImpl.instances(),
    private val dataStore: MyDataStore = MyDataStore.getInstances()
) : HomeScreenViewModel, ViewModel() {

    override val getAllBooksLiveData = MutableLiveData<List<BookData>>()
    override val getLastBooksLiveData = MutableLiveData<LastReadBook>()
    override val getAuthorsLiveData = MutableLiveData<List<AuthorData>>()
    override val clickItem = MutableLiveData<BookData>()
    override val clickMenuLiveData = MutableLiveData<Unit>()
    override val clickMoreButton = MutableLiveData<Unit>()

    override fun clickItemSelected(data: BookData) {
        clickItem.value = data
    }

    override fun getBooks() {
        useCase.downloadBooks()
            .onEach { result ->
                result.onSuccess {
                    getAllBooksLiveData.value = it
                }
                result.onFailure {

                }
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    override fun getLastReadBooks() {
        viewModelScope.launch {
            dataStore.getLastReadedBook().collectLatest {
                getLastBooksLiveData.value = it
            }
        }
    }

    override fun getAuthors() {
        useCase.downloadAuthors()
            .onEach { result ->
                result.onSuccess {
                    Log.d("TTT", "${it.size}")
                    getAuthorsLiveData.value = it
                }
                result.onFailure {
                    Log.d("TTT", "XATO")
                }
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    override fun clickMenu() {
        clickMenuLiveData.value = Unit
    }

    override fun clickMoreButton() {
        clickMoreButton.value = Unit
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
//        repository.downloadBook(url,"")
//    }


}