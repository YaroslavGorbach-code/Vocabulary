package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di

import dagger.Subcomponent
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui.DescriptionFragment

@Subcomponent(modules = [DescriptionModule::class])
interface DescriptionComponent {

    fun inject(descriptionFragment: DescriptionFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): DescriptionComponent
    }
}
