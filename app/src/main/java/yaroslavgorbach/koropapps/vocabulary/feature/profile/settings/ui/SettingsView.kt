package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentSettingsBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui.recycler.ThemesListAdapter

class SettingsView(private val binding: FragmentSettingsBinding, private val callback: Callback) {

    interface Callback {
        fun onChoseTheme(themes: List<Theme>)
    }

    private var themes: List<Theme> = emptyList()

    init {
        initViewActions()
    }

    private fun initViewActions() {
        binding.choseThemeItem.root.setOnClickListener {
            callback.onChoseTheme(themes)
        }
    }

    fun setThemes(themes: List<Theme>) {
        this.themes = themes
    }

    fun setCurrentTheme(theme: Theme) {
        binding.choseThemeItem.themeColorExample.setBackgroundColor(theme.colorPrimary)
    }
}