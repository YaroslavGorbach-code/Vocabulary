package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.di

import dagger.Subcomponent
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui.SettingsFragment

@InternalCoroutinesApi
@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {

    fun inject(settingsFragment: SettingsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }
}