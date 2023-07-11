package uz.gita.B5EBook.presentetion.ui.screens.desc

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.B5EBook.data.model.BookData

import uz.gita.B5EBook.presentetion.ui.screens.desc.impl.DescViewModelImpl
import uz.gita.B5EBook.utils.checkPermissions
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.ScreenDescBinding
import java.io.File


@AndroidEntryPoint
class DescScreen : Fragment(R.layout.screen_desc) {
    private val binding by viewBinding(ScreenDescBinding::bind)
    private val viewModel: DescViewModel by viewModels<DescViewModelImpl>()
    private val args by navArgs<DescScreenArgs>()
    var save = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.clickReadLiveData.observe(this, readObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val data = args.data
        viewModel.clickBackLiveData.observe(viewLifecycleOwner, openHomeObserver)
        viewModel.check.observe(viewLifecycleOwner, checkObserver)
        viewModel.error.observe(viewLifecycleOwner, errorObserver)
        viewModel.downloadMessage.observe(viewLifecycleOwner, downloadObserver)
        viewModel.clickSaveLiveData.observe(viewLifecycleOwner, clickSaveObserver)
//        viewModel.clickDownloadLiveData.observe(viewLifecycleOwner,downloadBookObserver)


        val file = File(requireContext().filesDir, data.reference)
        binding.apply {
            bookNameDesc.text = data.title
            bookAuthorDesc.text = data.author
            bookInfoDesc.text = data.description
            textScreen.text = data.title

            if (data.save.toInt() == 1) buttonSave.setImageResource(R.drawable.save_on)
            else buttonSave.setImageResource(R.drawable.save_off)

            if (file.exists()) {
                pdfImage.visibility = View.VISIBLE
                buttonOpenBook.visibility = View.VISIBLE
                appCompatImageView3.visibility = View.INVISIBLE
                buttonDownload.visibility = View.INVISIBLE
            } else {
                pdfImage.visibility = View.INVISIBLE
                appCompatImageView3.visibility = View.VISIBLE
                buttonOpenBook.visibility = View.INVISIBLE
                buttonDownload.visibility = View.VISIBLE

                buttonDownload.setOnClickListener {
                    val bar = CircularProgressDrawable(requireActivity())
                    bar.strokeWidth = 5f
                    bar.centerRadius = 28f
                    bar.start()
                    appCompatImageView3.setImageDrawable(bar)

                    checkPermissions(arrayListOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(requireContext(), "Waiting...", Toast.LENGTH_LONG).show()
                        viewModel.clickDownloadButton(requireContext(), data)
                    }
                }
            }

            descBack.setOnClickListener {
                viewModel.clickBackButton()
            }

//            readBookButton.setOnClickListener {
//                viewModel.clickReadButton(data)
//            }

            buttonOpenBook.setOnClickListener {
                if (file.exists()) {
                    viewModel.clickReadButton(data)
                } else {
                    Toast.makeText(requireContext(), "Download book!", Toast.LENGTH_LONG).show()
                }
            }

            buttonSave.setOnClickListener {
                save = if (save == "0") "1" else "0"
                viewModel.clickSaveButton(
                    BookData(
                        author = data.author,
                        bookUrl = data.bookUrl,
                        coverUrl = data.coverUrl,
                        genre = data.genre,
                        page = data.page,
                        rate = data.rate,
                        reference = data.reference,
                        title = data.title,
                        year = data.year,
                        readerPages = data.readerPages,
                        description = data.description,
                        save = save
                    )
                )
            }


            Glide
                .with(binding.root)
                .load(data.coverUrl)
                .centerCrop()
                .into(bookImage)
        }
    }

    private val readObserver = Observer<BookData> {
        findNavController().navigate(DescScreenDirections.actionDescScreenToReadScreen(it))
    }

    private val openHomeObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private val checkObserver = Observer<Boolean> {
        if (it) {
            binding.apply {
                appCompatImageView3.visibility = View.INVISIBLE
                buttonDownload.visibility = View.INVISIBLE
                buttonOpenBook.visibility = View.VISIBLE
                pdfImage.visibility = View.VISIBLE
            }
        }
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    private val downloadObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }

    private val clickSaveObserver = Observer<Int> {
        if (it == 1) {
            binding.buttonSave.setImageResource(R.drawable.save_on)
        } else {
            binding.buttonSave.setImageResource(R.drawable.save_off)
        }
    }
}