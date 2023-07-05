package uz.gita.firebaseexam.presentetion.ui.screens.read

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.barteksc.pdfviewer.util.FitPolicy
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.data.model.BookData
import uz.gita.firebaseexam.databinding.ScreenReadBinding
import uz.gita.firebaseexam.presentetion.ui.screens.read.viewmodel.ReadViewModel
import uz.gita.firebaseexam.presentetion.ui.screens.read.viewmodel.impl.ReadViewModelImpl
import java.io.File

class ReadScreen : Fragment(R.layout.screen_read) {
    private val binding by viewBinding(ScreenReadBinding::class.java)
    private val viewModel: ReadViewModel by viewModels<ReadViewModelImpl>()
    private val args by navArgs<ReadScreenArgs>()
    private lateinit var data: BookData


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        data = args.data
        viewModel.descScreenLiveData.observe(viewLifecycleOwner, openDescScreenObserver)
        openBook(requireContext(), data)

        binding.apply {
            buttonBack.setOnClickListener {
                viewModel.back()
            }

        }
    }

    private val openDescScreenObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private fun openBook(context: Context, bookData: BookData) {
        val file = File(context.filesDir, bookData.reference)
        binding.pdfView.fromFile(file).enableSwipe(true)
            .defaultPage(0)
            .swipeHorizontal(true)
            .pageSnap(true)
            .autoSpacing(true)
            .pageFling(true).load()
    }


}