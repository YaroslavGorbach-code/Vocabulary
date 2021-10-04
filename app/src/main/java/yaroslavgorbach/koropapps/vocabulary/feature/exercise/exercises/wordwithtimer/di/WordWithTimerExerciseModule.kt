package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.presentation.WordWithTimerViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class WordWithTimerExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(WordWithTimerViewModel::class)
    abstract fun bindDictionaryViewModel(viewModel: WordWithTimerViewModel): ViewModel
}