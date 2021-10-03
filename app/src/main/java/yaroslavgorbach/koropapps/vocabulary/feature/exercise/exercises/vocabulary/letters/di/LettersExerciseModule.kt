package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.letters.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.letters.presentation.LettersExerciseViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class LettersExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(LettersExerciseViewModel::class)
    abstract fun bindViewModel(viewModel: LettersExerciseViewModel): ViewModel
}