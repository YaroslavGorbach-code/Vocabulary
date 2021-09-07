package yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.di

import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.ui.ProfileFragment

@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {

    fun inject(descriptionFragment: ProfileFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ProfileComponent
    }
}
