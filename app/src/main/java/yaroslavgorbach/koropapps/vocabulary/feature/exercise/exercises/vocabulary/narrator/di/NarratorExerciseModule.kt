package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.narrator.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.narrator.presentation.NarratorViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class NarratorExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(NarratorViewModel::class)
    abstract fun bindNarratorViewModel(viewModel: NarratorViewModel): ViewModel
}