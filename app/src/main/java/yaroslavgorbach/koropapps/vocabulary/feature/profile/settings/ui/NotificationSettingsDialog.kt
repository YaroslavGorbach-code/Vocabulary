package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.DialogNotificationSettingsBinding

class NotificationSettingsDialog : DialogFragment() {

    private var _binding: DialogNotificationSettingsBinding? = null

    private val binding: DialogNotificationSettingsBinding
        get() = requireNotNull(_binding)


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViews()

        return MaterialAlertDialogBuilder(requireContext())
            .setPositiveButton(R.string.ok, null)
            .setTitle(R.string.notifications_settings)
            .setView(binding.root)
            .create()
    }

    private fun initViews() {
        _binding = DialogNotificationSettingsBinding.inflate(LayoutInflater.from(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}