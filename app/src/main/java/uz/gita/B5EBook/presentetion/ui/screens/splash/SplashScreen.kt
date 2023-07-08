package uz.gita.B5EBook.presentetion.ui.screens.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.B5EBook.presentetion.ui.screens.splash.viewmodel.SplashViewModel
import uz.gita.B5EBook.presentetion.ui.screens.splash.viewmodel.impl.SplashViewModelImpl
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.ScreenSplashBinding

class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding<ScreenSplashBinding>()
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    private lateinit var h: Handler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openHomeScreenLiveData.observe(viewLifecycleOwner, openHomeScreenObserver)

        h = Handler(Looper.getMainLooper())

        h.postDelayed({
            viewModel.openHomeScreen()
        }, 1500)
    }


    private val openHomeScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
    }
}