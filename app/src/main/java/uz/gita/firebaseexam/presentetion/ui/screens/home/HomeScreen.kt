package uz.gita.firebaseexam.presentetion.ui.screens.home

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.data.model.AuthorData
import uz.gita.firebaseexam.data.model.BookData
import uz.gita.firebaseexam.data.model.LastReadBook
import uz.gita.firebaseexam.databinding.ScreenHomeBinding
import uz.gita.firebaseexam.presentetion.adapter.AuthorAdapter
import uz.gita.firebaseexam.presentetion.adapter.HomeAdapter
import uz.gita.firebaseexam.presentetion.dialog.MyBottomSheetDialog
import uz.gita.firebaseexam.presentetion.ui.screens.home.viewModel.HomeScreenViewModel
import uz.gita.firebaseexam.presentetion.ui.screens.home.viewModel.impl.HomeScreenViewModelImpl


class HomeScreen : Fragment(R.layout.screen_home) {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private val viewModel: HomeScreenViewModel by viewModels<HomeScreenViewModelImpl>()
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController

    private lateinit var adapter: HomeAdapter
    private lateinit var authorAdapter: AuthorAdapter

    private lateinit var dialog: MyBottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.clickItem.observe(this, openDescScreen)
        viewModel.clickMoreButton.observe(this, openBottomSheetDialogFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = HomeAdapter()
        authorAdapter = AuthorAdapter()
        viewModel.getAllBooksLiveData.observe(viewLifecycleOwner, getAllBooksLiveData)
        viewModel.getAuthorsLiveData.observe(viewLifecycleOwner, getAllAuthorsLiveData)
        viewModel.getLastBooksLiveData.observe(viewLifecycleOwner, getLastBookLiveData)
        viewModel.clickMenuLiveData.observe(viewLifecycleOwner, openMenuObserver)

        viewModel.getBooks()
        viewModel.getLastReadBooks()
        viewModel.getAuthors()


        binding.apply {
            drawer = drawLayout
            this@HomeScreen.navigationView = navigationView
            navigationView.itemIconTintList = null
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            NavigationUI.setupWithNavController(navigationView, navController)

            recyclerBook.adapter = adapter
            recyclerBook.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

            recyclerView.adapter = authorAdapter
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

            adapter.setClickBookItem {
                viewModel.clickItemSelected(it)
            }

//            buttonMenu.setOnClickListener {
//                viewModel.clickMenu()
//            }

            buttonMore.setOnClickListener {
                viewModel.clickMoreButton()
            }

        }

    }

    private val openDescScreen = Observer<BookData> {
        findNavController().navigate(HomeScreenDirections.actionHomeScreenToDescScreen(it))
    }

    private val getAllBooksLiveData = Observer<List<BookData>> {
        adapter.submitList(it)
    }

    private val getAllAuthorsLiveData = Observer<List<AuthorData>> {
        authorAdapter.submitList(it)
    }

    private val getLastBookLiveData = Observer<LastReadBook> {
        if (it.author.isEmpty()) {

        } else {

        }
    }

    private val openMenuObserver = Observer<Unit> {
        binding.drawLayout.openDrawer(GravityCompat.START)
    }

    private val openBottomSheetDialogFragment = Observer<Unit> {
        dialog = MyBottomSheetDialog()
        dialog.show(parentFragmentManager, "dialog")
    }
}