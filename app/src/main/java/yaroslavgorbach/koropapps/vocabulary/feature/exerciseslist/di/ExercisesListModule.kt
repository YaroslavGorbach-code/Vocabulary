package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.presentation.ExercisesListViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class ExercisesListModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExercisesListViewModel::class)
    abstract fun bindWeatherViewModel(viewModel: ExercisesListViewModel): ViewModel
}