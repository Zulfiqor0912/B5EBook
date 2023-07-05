package uz.gita.firebaseexam.presentetion.ui.screens.read.viewmodel

import androidx.lifecycle.LiveData

interface ReadViewModel {
    val descScreenLiveData:LiveData<Unit>

    fun back()
}