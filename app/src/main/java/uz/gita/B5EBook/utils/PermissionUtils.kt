package uz.gita.B5EBook.utils

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

fun Fragment.checkPermissions(array: List<String>, block: () -> Unit) {
    Dexter.withContext(requireContext()).withPermissions(array).withListener(object :
        MultiplePermissionsListener {
        override fun onPermissionsChecked(report: MultiplePermissionsReport) {
            report.let {
                if (report.areAllPermissionsGranted()) {
                    block.invoke()
                }
            }
        }

        override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:${requireActivity().packageName}")
            startActivity(intent)
        }
    }).check()
}