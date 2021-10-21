package yaroslavgorbach.koropapps.vocabulary.feature.common.uikit

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.utils.host

class PermissionDeniedDialog : DialogFragment() {

    interface Host {
        fun onGrantPermissionClicked()
    }

    companion object {
        private const val ARG_DIALOG_MESSAGE = "ARG_DIALOG_MESSAGE"

        fun newInstance(message: String) = PermissionDeniedDialog().apply {
            arguments = bundleOf(ARG_DIALOG_MESSAGE to message)
        }
    }

    private val message: String
        get() = requireArguments()[ARG_DIALOG_MESSAGE] as String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.permission_denied_error_title)
            .setMessage(message)
            .setPositiveButton(R.string.grant_permission) { _, _ ->
                host<Host>().onGrantPermissionClicked()
            }
            .setNegativeButton(R.string.close, null)
            .create()
            .apply { isCancelable = false }
    }

}