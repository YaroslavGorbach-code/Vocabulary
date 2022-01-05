package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.utils.host

class ClearAllDataConfirmationDialog : DialogFragment() {

    interface Host {
        fun onClearAllData()
    }

    companion object {
        fun newInstance() = ClearAllDataConfirmationDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(R.string.this_action_is_impossible_to_cancel)
            setMessage(R.string.clear_all_data_confirmation_message)
            setPositiveButton(R.string.clear_read) { _, _ -> host<Host>().onClearAllData() }
            setNegativeButton(R.string.cancel) { _, _ -> }
        }.create()
    }

}