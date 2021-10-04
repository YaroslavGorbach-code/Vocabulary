package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.word.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.word.presentation.WordExerciseViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class WordExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(WordExerciseViewModel::class)
    abstract fun bindViewModel(viewModel: WordExerciseViewModel): ViewModel
}