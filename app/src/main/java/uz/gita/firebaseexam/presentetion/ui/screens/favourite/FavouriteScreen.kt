package uz.gita.firebaseexam.presentetion.ui.screens.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.ScreenFavouriteBinding
import uz.gita.firebaseexam.presentetion.ui.screens.favourite.viewmodel.FavouriteScreenViewModel
import uz.gita.firebaseexam.presentetion.ui.screens.favourite.viewmodel.impl.FavouriteScreenViewModelImpl


class FavouriteScreen : Fragment(R.layout.screen_favourite) {
    private val binding by viewBinding<ScreenFavouriteBinding>()
    private val viewModel: FavouriteScreenViewModel by viewModels<FavouriteScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}