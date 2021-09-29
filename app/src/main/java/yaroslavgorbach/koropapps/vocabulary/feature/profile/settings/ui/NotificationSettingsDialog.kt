package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.databinding.DialogNotificationSettingsBinding
import yaroslavgorbach.koropapps.vocabulary.utils.host
import yaroslavgorbach.koropapps.vocabulary.utils.unsafeLazy

class NotificationSettingsDialog : DialogFragment() {

    interface Host {
        fun onNotificationChanged(notification: Notification)
    }

    companion object {
        private const val NOTIFICATION_ARG = "NOTIFICATION_ARG"

        fun newInstance(notification: Notification): NotificationSettingsDialog {
            return NotificationSettingsDialog().apply {
                arguments = Bundle().apply {
                    putParcelable(NOTIFICATION_ARG, notification)
                }
            }
        }
    }

    private var _binding: DialogNotificationSettingsBinding? = null

    private val binding: DialogNotificationSettingsBinding
        get() = requireNotNull(_binding)

    private val notification: Notification by unsafeLazy {
        requireNotNull(requireArguments().getParcelable(NOTIFICATION_ARG))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initViews()
        initViewActions()

        return MaterialAlertDialogBuilder(requireContext())
            .setPositiveButton(R.string.save) { _, _ -> saveNotificationChanges() }
            .setTitle(R.string.notifications_settings)
            .setView(binding.root)
            .create()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        _binding = DialogNotificationSettingsBinding.inflate(LayoutInflater.from(requireContext()))

        with(notification) {
            binding.notificationCheckBox.isChecked = isActive

            binding.notificationText.setText(text)

            binding.notificationTime.text = "$hour:$minute"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViewActions() {
        binding.notificationTime.setOnClickListener {
            showTimePickerDialog { hour, minute ->
                notification.hour = hour
                notification.minute = minute

                binding.notificationTime.text = "$hour:$minute"
            }
        }
    }

    private fun showTimePickerDialog(onTimeChanged: (h: Int, m: Int) -> Unit) {
        TimePickerDialog(
            requireContext(),
            { _, hour, minute -> onTimeChanged(hour, minute) },
            notification.hour,
            notification.minute,
            DateFormat.is24HourFormat(requireActivity())
        ).show()
    }

    private fun saveNotificationChanges() {
        if (isNotificationTextValid()) {
            notification.text = binding.notificationText.text.toString()
        }

        notification.isActive = binding.notificationCheckBox.isChecked

        host<Host>().onNotificationChanged(notification)
    }

    private fun isNotificationTextValid(): Boolean {
        return binding.notificationText.text.isNullOrEmpty().not()
                && binding.notificationText.text.isNullOrBlank().not()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}