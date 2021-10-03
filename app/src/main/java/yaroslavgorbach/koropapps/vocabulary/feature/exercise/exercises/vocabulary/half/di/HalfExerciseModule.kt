package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.half.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.half.presentation.HalfViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class HalfExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(HalfViewModel::class)
    abstract fun bindHalfViewModel(viewModel: HalfViewModel): ViewModel
}