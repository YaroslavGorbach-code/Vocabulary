package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.presentation.TautogramsViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class TautogramsExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(TautogramsViewModel::class)
    abstract fun bindTautogramsViewModel(viewModel: TautogramsViewModel): ViewModel
}