package yaroslavgorbach.koropapps.vocabulary.feature.training.di

import dagger.Subcomponent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.TrainingFragment

@Subcomponent(modules = [TrainingModule::class])
interface TrainingComponent {

    @FlowPreview
    @InternalCoroutinesApi
    fun inject(trainingFragment: TrainingFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TrainingComponent
    }
}
