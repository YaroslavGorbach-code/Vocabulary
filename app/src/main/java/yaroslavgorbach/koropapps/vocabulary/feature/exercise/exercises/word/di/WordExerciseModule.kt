package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.FeatureUtilModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.presentation.WordExerciseViewModel

@Module(includes = [ViewModelBuilderModule::class, FeatureUtilModule::class])
abstract class WordExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(WordExerciseViewModel::class)
    abstract fun bindViewModel(viewModel: WordExerciseViewModel): ViewModel
}