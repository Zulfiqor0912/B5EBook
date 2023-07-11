package uz.gita.B5EBook.presentetion.ui.screens.read

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.github.barteksc.pdfviewer.util.FitPolicy
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.data.source.local.LocalStorage
import uz.gita.B5EBook.presentetion.ui.screens.read.impl.ReadViewModelImpl
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.ScreenReadBinding
import java.io.File

@AndroidEntryPoint
class ReadScreen : Fragment(R.layout.screen_read) {
    private val binding by viewBinding(ScreenReadBinding::class.java)
    private val viewModel: ReadViewModel by viewModels<ReadViewModelImpl>()
    private val args by navArgs<ReadScreenArgs>()
    private val localStorage = LocalStorage.getInstance()
    private lateinit var data: BookData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.descScreenLiveData.observe(this, openDescScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentPageLiveData.observe(viewLifecycleOwner, currentPageObserver)
        data = args.data
        openBook(requireContext(), data)

        binding.apply {

            buttonBack.setOnClickListener {
                viewModel.back()
            }

        }
    }

    private val openDescScreenObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

    private fun openBook(context: Context, bookData: BookData) {
        val pageNumber: Int

        if (localStorage?.getLogicRead() == false) {
            pageNumber = localStorage.getBookPage(localStorage.getBookName()!!) as Int

            val file = localStorage.getReference()?.let { File(context.filesDir, it) }
            binding.pdfView
                .fromFile(file)
                .enableSwipe(true)
                .defaultPage(pageNumber)
                .swipeHorizontal(false)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .onPageChange { page, count ->
                    localStorage.saveBookPage(localStorage.getBookName()!!, page)
                    viewModel.currentPage(page)
                }
                .enableDoubletap(true)
                .enableAnnotationRendering(false)
                .scrollHandle(DefaultScrollHandle(context))
                .enableAntialiasing(true)
                .spacing(10)
                .nightMode(false)
                .pageFitPolicy(FitPolicy.BOTH)
                .load()

            binding.apply {
                currentPageNum.text = pageNumber.toString()
                pages.text = localStorage.getBookPage(localStorage.getBookName()!!).toString()
            }
        } else {
            pageNumber = localStorage?.getBookPage(bookData.title) as Int

            val file = File(context.filesDir, bookData.reference)
            binding.pdfView
                .fromFile(file)
                .enableSwipe(true)
                .defaultPage(pageNumber)
                .swipeHorizontal(false)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .onPageChange { page, count ->
                    localStorage.saveBookPage(bookData.title, page)
                    viewModel.currentPage(page)
                }
                .enableDoubletap(true)
                .enableAnnotationRendering(false)
                .scrollHandle(DefaultScrollHandle(context))
                .enableAntialiasing(true)
                .spacing(10)
                .nightMode(false)
                .pageFitPolicy(FitPolicy.BOTH)
                .load()

            binding.apply {
                currentPageNum.text = pageNumber.toString()
                pages.text = bookData.page
            }

            localStorage.saveReference(bookData.reference)
            localStorage.saveBookName(bookData.title)
            localStorage.saveBookAuthor(bookData.author)
            localStorage.saveBookImage(bookData.coverUrl)
        }
    }

    private val currentPageObserver = Observer<Int> {
//        val int = (data.page.toInt() / 100) * it
//        val wudth = (binding.pdfView.width / 100) * int
//        binding.appCompatImageView.s
        viewModel.updateBook(
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
                readerPages = it,
                description = data.description,
                save = data.save
            )
        )
        binding.currentPageNum.text = it.toString()
    }


}