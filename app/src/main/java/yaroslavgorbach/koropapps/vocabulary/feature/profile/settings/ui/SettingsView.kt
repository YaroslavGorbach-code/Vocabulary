package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.annotation.SuppressLint
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentSettingsBinding

class SettingsView(private val binding: FragmentSettingsBinding, private val callback: Callback) {

    interface Callback {
        fun onChoseTheme(themes: List<Theme>, uiMode: UiMode)

        fun onNotificationSettings(notification: Notification)

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
    }

    fun setThemes(themes: List<Theme>) {
        this.themes = themes
    }

    fun setCurrentTheme(theme: Theme) {
        binding.choseThemeItem.themeColorExample.setBackgroundColor(theme.colorPrimary)
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