package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.presentation.AntonymsSynonymsViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.presentation.AssociationsViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class AssociationsExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(AssociationsViewModel::class)
    abstract fun bindWeatherViewModel(viewModel: AssociationsViewModel): ViewModel
}