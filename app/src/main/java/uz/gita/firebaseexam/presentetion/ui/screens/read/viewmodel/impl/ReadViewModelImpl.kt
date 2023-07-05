package uz.gita.firebaseexam.presentetion.ui.screens.read.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.firebaseexam.presentetion.ui.screens.read.viewmodel.ReadViewModel

class ReadViewModelImpl : ReadViewModel, ViewModel() {
    override val descScreenLiveData = MutableLiveData<Unit>()

    override fun back() {
        descScreenLiveData.value = Unit
    }
}