package uz.gita.B5EBook.presentetion.ui.screens.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.ScreenInfoBinding

class InfoScreen : Fragment(R.layout.screen_info) {
    private val binding by viewBinding<ScreenInfoBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            infoBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}