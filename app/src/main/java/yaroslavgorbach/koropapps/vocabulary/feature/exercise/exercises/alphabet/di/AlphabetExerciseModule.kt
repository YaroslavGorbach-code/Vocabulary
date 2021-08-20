package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.presentation.AlphabetViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui.AlphabetView
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.presentation.AntonymsSynonymsViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class AlphabetExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(AlphabetViewModel::class)
    abstract fun bindWeatherViewModel(viewModel: AlphabetViewModel): ViewModel
}