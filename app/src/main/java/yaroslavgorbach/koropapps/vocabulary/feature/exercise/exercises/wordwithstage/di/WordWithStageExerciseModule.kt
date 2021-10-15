package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.presentation.WordWithStageViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class WordWithStageExerciseModule {

    @Binds
    @IntoMap
    @ViewModelKey(WordWithStageViewModel::class)
    abstract fun bindViewModel(viewModel: WordWithStageViewModel): ViewModel

}

