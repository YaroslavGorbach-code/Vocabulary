package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.specifications.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.specifications.presentation.SpecificationsViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class SpecificationsExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(SpecificationsViewModel::class)
    abstract fun bindSpecificationsViewModel(viewModel: SpecificationsViewModel): ViewModel
}