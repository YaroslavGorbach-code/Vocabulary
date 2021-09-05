package yaroslavgorbach.koropapps.vocabulary.feature.profile.di

import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.presentation.ProfileViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.profile.ui.ProfileFragment

@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {

    fun inject(descriptionFragment: ProfileFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ProfileComponent
    }
}
