package uz.gita.B5EBook.presentetion.ui.screens.saved

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.presentetion.adapter.HomeAdapter
import uz.gita.B5EBook.presentetion.ui.screens.saved.impl.SavedViewModelImpl
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.ScreenSavedBinding


@AndroidEntryPoint
class SavedScreen : Fragment(R.layout.screen_saved) {
    private val binding by viewBinding<ScreenSavedBinding>()
    private val viewModel: SavedViewModel by viewModels<SavedViewModelImpl>()
    private lateinit var adapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HomeAdapter()
        viewModel.loadBooks.observe(viewLifecycleOwner, getAllBooks)
        viewModel.openHomeScreenLiveData.observe(viewLifecycleOwner, openHomeScreenObserver)
        viewModel.clickItemSelected.observe(viewLifecycleOwner, openDescScreenObserver)


        binding.apply {
            savedRecycler.adapter = adapter
            savedRecycler.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)


            savedBack.setOnClickListener {
                viewModel.clickBackButton()
            }

            adapter.setClickBookItem {
                viewModel.clickItemSelected(it)
            }
        }

    }

    private val getAllBooks = Observer<List<BookData>> {
        if (it.isNotEmpty()) {
            adapter.submitList(it)
        } else {

        }
    }

    private val openHomeScreenObserver = Observer<Unit> {
        findNavController().popBackStack()
    }

    private val openDescScreenObserver = Observer<BookData> {
        findNavController().navigate(SavedScreenDirections.actionSaveScreenToDescScreen(it))
    }
}