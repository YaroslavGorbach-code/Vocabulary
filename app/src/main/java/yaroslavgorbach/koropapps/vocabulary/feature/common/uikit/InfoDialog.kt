package yaroslavgorbach.koropapps.vocabulary.feature.common.uikit

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.utils.host

class InfoDialog : DialogFragment() {

    interface Host {
        fun onInfoDialogCancel()
    }

    companion object {
        const val ARG_TITLE = "ARG_TITLE"
        const val ARG_MESSAGE = "ARG_MESSAGE"

        fun newInstance(title: String?, message: String?) = InfoDialog().apply {
            arguments = bundleOf(
                ARG_TITLE to title,
                ARG_MESSAGE to message
            )
        }
    }

    private val title: String?
        get() = requireArguments().getString(ARG_TITLE)

    private val message: String?
        get() = requireArguments().getString(ARG_MESSAGE)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).apply {

            if (title.isNullOrEmpty().not()) {
                setTitle(title)
            }

            if (message.isNullOrEmpty().not()) {
                setMessage(message)
            }
        }.create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        host<Host>().onInfoDialogCancel()
    }
}