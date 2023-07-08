package uz.gita.B5EBook.presentetion.ui.screens.splash.viewmodel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    fun openHomeScreen()
    val  openHomeScreenLiveData:LiveData<Unit>
}