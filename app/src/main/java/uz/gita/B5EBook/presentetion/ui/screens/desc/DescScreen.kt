package uz.gita.B5EBook.presentetion.ui.screens.desc

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.B5EBook.data.model.BookData

import uz.gita.B5EBook.presentetion.ui.screens.desc.viewModel.DescScreenViewModel
import uz.gita.B5EBook.presentetion.ui.screens.desc.viewModel.impl.DescScreenViewModelImpl
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.ScreenDescBinding


class DescScreen : Fragment(R.layout.screen_desc) {
    private val binding by viewBinding(ScreenDescBinding::bind)
    private val viewModel: DescScreenViewModel by viewModels<DescScreenViewModelImpl>()
    private val args by navArgs<DescScreenArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = args.data
        viewModel.clickReadLiveData.observe(viewLifecycleOwner, readObserver)
        viewModel.clickBackLiveData.observe(viewLifecycleOwner, openHomeObserver)
//        viewModel.clickDownloadLiveData.observe(viewLifecycleOwner,downloadBookObserver)


        binding.apply {
            bookNameDesc.text = data.title
            bookAuthorDesc.text = data.author
            bookInfoDesc.text = data.description


            descBack.setOnClickListener {
                viewModel.clickBackButton()
            }

            readBookButton.setOnClickListener {
                viewModel.clickReadButton(data)
            }

            buttonDownload.setOnClickListener {
                context?.let { it1 -> viewModel.clickDownloadButton(it1, data) }
            }


            Glide.with(binding.root)
                .load(data.coverUrl)
                .into(bookImage)
        }
    }

    private val readObserver = Observer<BookData> {
        findNavController().navigate(DescScreenDirections.actionDescScreenToReadScreen(it))
    }

    private val openHomeObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
}