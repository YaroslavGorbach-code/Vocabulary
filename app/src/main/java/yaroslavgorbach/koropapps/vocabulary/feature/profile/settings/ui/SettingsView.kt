package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.annotation.SuppressLint
import com.google.android.material.snackbar.Snackbar
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentSettingsBinding

class SettingsView(private val binding: FragmentSettingsBinding, private val callback: Callback) {

    interface Callback {
        fun onChoseTheme(themes: List<Theme>, uiMode: UiMode)
        fun onNotificationSettings(notification: Notification)
        fun onAutoRecording(isChecked: Boolean)
        fun onKeepScreenOn(isChecked: Boolean)
        fun onClearAllData()
        fun onBack()
    }

    private var themes: List<Theme> = emptyList()

    private var uiMode: UiMode = UiMode.Light()

    private var notification: Notification? = null

    init {
        initViewActions()
    }

    private fun initViewActions() {
        binding.choseThemeItem.root.setOnClickListener {
            callback.onChoseTheme(themes, uiMode)
        }

        binding.notificationsSettingsItem.root.setOnClickListener {
            callback.onNotificationSettings(requireNotNull(notification))
        }

        binding.settingsToolbar.setNavigationOnClickListener {
            callback.onBack()
        }

        binding.autoRecording.autoRecordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            callback.onAutoRecording(isChecked)
        }

        binding.keepScreenOnSetting.keepScreenOnCheckBox.setOnCheckedChangeListener { _, isChecked ->
            callback.onKeepScreenOn(isChecked)
        }

        binding.autoRecording.root.setOnClickListener {
            binding.autoRecording.autoRecordCheckBox.isChecked =
                binding.autoRecording.autoRecordCheckBox.isChecked.not()
        }

        binding.keepScreenOnSetting.root.setOnClickListener {
            binding.keepScreenOnSetting.keepScreenOnCheckBox.isChecked =
                binding.keepScreenOnSetting.keepScreenOnCheckBox.isChecked.not()
        }

        binding.clearData.root.setOnClickListener {
            callback.onClearAllData()
        }
    }

    fun setThemes(themes: List<Theme>) {
        this.themes = themes
    }

    fun setCurrentTheme(theme: Theme) {
        binding.choseThemeItem.themeColorExample.setBackgroundColor(
            binding.root.resources.getColor(
                theme.colorPrimaryRes
            )
        )
    }

    fun showAllDataClearedSnack() {
        Snackbar.make(binding.root, R.string.data_cleared, Snackbar.LENGTH_SHORT).show()
    }

    fun setAutoRecordStateChecked(isChecked: Boolean) {
        binding.autoRecording.autoRecordCheckBox.isChecked = isChecked
    }

    fun setKeepScreenOnStateChecked(isChecked: Boolean) {
        binding.keepScreenOnSetting.keepScreenOnCheckBox.isChecked = isChecked
    }

    @SuppressLint("SetTextI18n")
    fun setNotification(notification: Notification) {
        this.notification = notification

        binding.notificationsSettingsItem.notificationHourMinute.text =
            "${notification.hour}:${notification.minute}"
    }

    fun setUiMode(uiMode: UiMode) {
        this.uiMode = uiMode
    }
}