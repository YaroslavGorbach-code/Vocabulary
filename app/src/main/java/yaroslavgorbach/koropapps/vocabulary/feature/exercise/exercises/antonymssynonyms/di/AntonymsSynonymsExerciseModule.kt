package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.presentation.AntonymsSynonymsViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class AntonymsSynonymsExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(AntonymsSynonymsViewModel::class)
    abstract fun bindWeatherViewModel(viewModel: AntonymsSynonymsViewModel): ViewModel
}