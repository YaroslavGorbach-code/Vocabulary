package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentSettingsBinding

class SettingsView(private val binding: FragmentSettingsBinding, private val callback: Callback) {

    interface Callback {
        fun onChoseTheme()
    }

    init {
        initViewActions()
    }

    private fun initViewActions() {
        binding.choseTheme.root.setOnClickListener {
            callback.onChoseTheme()
        }

    }

    fun setTheme(theme: Theme) {

    }

}