package uz.gita.firebaseexam.presentetion.ui.screens.splash.viewmodel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    fun openHomeScreen()
    val  openHomeScreenLiveData:LiveData<Unit>
}