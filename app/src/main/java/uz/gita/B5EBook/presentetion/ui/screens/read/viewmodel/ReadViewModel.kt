package uz.gita.B5EBook.presentetion.ui.screens.read.viewmodel

import androidx.lifecycle.LiveData

interface ReadViewModel {
    val descScreenLiveData:LiveData<Unit>

    fun back()
}