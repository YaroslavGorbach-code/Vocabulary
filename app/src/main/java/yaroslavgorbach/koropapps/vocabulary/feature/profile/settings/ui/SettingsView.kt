package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentSettingsBinding

class SettingsView(private val binding: FragmentSettingsBinding, private val callback: Callback) {

    interface Callback {
        fun onChoseTheme(themes: List<Theme>, uiMode: UiMode)
        fun onBack()
    }

    private var themes: List<Theme> = emptyList()

    private var uiMode: UiMode = UiMode.Light()

    init {
        initViewActions()
    }

    private fun initViewActions() {
        binding.choseThemeItem.root.setOnClickListener {
            callback.onChoseTheme(themes, uiMode)
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

    fun setUiMode(uiMode: UiMode) {
        this.uiMode = uiMode
    }
}