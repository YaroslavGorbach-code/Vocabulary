package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.dictionary.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.dictionary.presentation.DictionaryViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class DictionaryExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(DictionaryViewModel::class)
    abstract fun bindDictionaryViewModel(viewModel: DictionaryViewModel): ViewModel
}