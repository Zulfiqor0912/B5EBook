package uz.gita.firebaseexam.presentetion.ui.screens.favourite.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.firebaseexam.presentetion.ui.screens.favourite.viewmodel.FavouriteScreenViewModel

class FavouriteScreenViewModelImpl : FavouriteScreenViewModel, ViewModel() {
    override val openHomeScreenLiveData = MutableLiveData<Unit>()
    override fun clickBackButton() {
        openHomeScreenLiveData.value = Unit
    }
}