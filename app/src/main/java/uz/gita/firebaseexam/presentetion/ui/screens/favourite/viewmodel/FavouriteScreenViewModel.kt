package uz.gita.firebaseexam.presentetion.ui.screens.favourite.viewmodel

import androidx.lifecycle.LiveData

interface FavouriteScreenViewModel {
    val openHomeScreenLiveData: LiveData<Unit>
    fun clickBackButton()

}