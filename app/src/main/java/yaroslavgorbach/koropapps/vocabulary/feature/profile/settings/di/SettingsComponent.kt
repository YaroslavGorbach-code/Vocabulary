package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.di

import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui.SettingsFragment

@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {

    fun inject(settingsFragment: SettingsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }
}