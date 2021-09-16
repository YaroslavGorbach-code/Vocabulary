package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R

class DialogChoseTheme : DialogFragment() {

    companion object {
        fun newInstance() = DialogChoseTheme()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.chose_theme)
            .create()
    }
}