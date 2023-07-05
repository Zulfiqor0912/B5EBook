package uz.gita.firebaseexam.presentetion.ui.screens.splash.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.firebaseexam.presentetion.ui.screens.splash.viewmodel.SplashViewModel

class SplashViewModelImpl : SplashViewModel, ViewModel() {

    override fun openHomeScreen() {
        openHomeScreenLiveData.value = Unit
    }

    override val openHomeScreenLiveData = MutableLiveData<Unit>()

}