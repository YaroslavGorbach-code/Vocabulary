package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.threeletters.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.threeletters.presentation.ThreeLettersViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class ThreeLettersExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(ThreeLettersViewModel::class)
    abstract fun bindThreeLettersViewModel(viewModel: ThreeLettersViewModel): ViewModel
}