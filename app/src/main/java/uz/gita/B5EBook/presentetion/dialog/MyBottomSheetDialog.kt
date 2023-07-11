package uz.gita.B5EBook.presentetion.dialog

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karumi.dexter.BuildConfig
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.B5EBook.presentetion.ui.screens.home.HomeViewModel
import uz.gita.B5EBook.presentetion.ui.screens.home.impl.HomeViewModelImpl
import uz.gita.firebaseexam.R
import uz.gita.firebaseexam.databinding.DialogBottomsheetBinding

@AndroidEntryPoint
class MyBottomSheetDialog : BottomSheetDialogFragment() {
    private val binding by viewBinding<DialogBottomsheetBinding>()
    private val viewModel:HomeViewModel by viewModels<HomeViewModelImpl>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            saves.setOnClickListener {
                openSaveScreen()
            }

            shareApp.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
                var shareMessage = "I recommend this app: Book App".trim() + "\n"
                shareMessage =
                    "${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}".trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Share This App"))
            }

            rateApp.setOnClickListener {
                val uri: Uri = Uri.parse("market://details?id=${requireContext().packageName}")
                val goToMarket: Intent = Intent(Intent.ACTION_VIEW, uri)
                goToMarket.addFlags(
                    Intent.FLAG_ACTIVITY_NO_HISTORY or
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                )
                try {
                    startActivity(goToMarket)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=${requireContext().packageName}")
                        )
                    )
                }
                dismiss()
            }

            aboutApp.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_infoScreen)
                dismiss()
            }

            quitApp.setOnClickListener {
                finish()
            }
        }
    }


    private fun openSaveScreen() {
        findNavController().navigate(R.id.action_homeScreen_to_saveScreen)
        dismiss()
    }

    private fun finish() {
        dismiss()
        requireActivity().finish()
    }
}