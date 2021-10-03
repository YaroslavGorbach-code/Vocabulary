package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.communication.abbreviations.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.communication.abbreviations.presentation.AbbreviationsViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class AbbreviationExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(AbbreviationsViewModel::class)
    abstract fun bindViewModel(viewModel: AbbreviationsViewModel): ViewModel
}
