package uz.gita.B5EBook.presentetion.ui.screens.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.presentetion.adapter.HomeAdapter
import uz.gita.B5EBook.presentetion.dialog.MyBottomSheetDialog
import uz.gita.B5EBook.presentetion.ui.screens.home.impl.HomeViewModelImpl
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.ScreenHomeBinding


@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    private lateinit var adapter: HomeAdapter

    private lateinit var dialog: MyBottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.clickItem.observe(this, openDescScreen)
        viewModel.clickMoreButton.observe(this, openBottomSheetDialogFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = HomeAdapter()

        viewModel.loadBooks()

        viewModel.searchWordsLiveData.observe(viewLifecycleOwner, searchResultObserver)
        viewModel.loadBooks.observe(viewLifecycleOwner, getAllBooksLiveData)
        viewModel.finishLiveData.observe(viewLifecycleOwner,finishAppObserver)


        binding.apply {

            recyclerBook.adapter = adapter
            recyclerBook.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

            adapter.setClickBookItem {
                viewModel.clickItemSelected(it)
            }

            buttonMore.setOnClickListener {
                viewModel.clickMoreButton()
            }


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (query.isNotEmpty()) {
                            viewModel.search("%${it.trim()}%")
                            binding.isSearchEmpty.visibility = View.VISIBLE
                        } else {
                            binding.isSearchEmpty.visibility = View.GONE
                            Log.d("YYY", "${viewModel.loadBooks.value?.size}")
                            adapter.submitList(viewModel.loadBooks.value)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        if (newText.isNotEmpty()) {
                            viewModel.search("%${it.trim()}%")
                            binding.isSearchEmpty.visibility = View.VISIBLE
                        } else {
                            binding.isSearchEmpty.visibility = View.GONE
                            Log.d("LLL", "${viewModel.loadBooks.value?.size}")
                            adapter.submitList(viewModel.loadBooks.value)
                        }
                    }

                    return true
                }

            })
        }
    }

    private val openDescScreen = Observer<BookData> {
        Log.d("DES", it.description)
        findNavController().navigate(HomeScreenDirections.actionHomeScreenToDescScreen(it))
    }

    private val getAllBooksLiveData = Observer<List<BookData>> { list ->
//        for (i in list.indices){
//            if
//        }
        if (list.isNotEmpty()) {
            binding.isSearchEmpty.visibility = View.GONE
        } else {
            binding.isSearchEmpty.visibility = View.GONE
        }

        adapter.submitList(list)
        Log.d("LLL", "${list.size}")
    }

    private val openBottomSheetDialogFragment = Observer<Unit> {
        dialog = MyBottomSheetDialog()
        dialog.show(parentFragmentManager, "dialog")
    }

    private val searchResultObserver = Observer<List<BookData>> { list ->
        if (list.isNotEmpty()) {
            binding.isSearchEmpty.visibility = View.GONE
        } else {
            binding.isSearchEmpty.visibility = View.VISIBLE
        }
        for (element in list) {
            Log.d("QQQ", element.title)
        }
        adapter.submitList(list)
    }

    private val finishAppObserver = Observer<Boolean> {
        if (it){
            findNavController().navigateUp()
        }
    }
}