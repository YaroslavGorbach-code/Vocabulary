package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.rememberall.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.rememberall.presentation.RememberAllViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class RememberAllExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(RememberAllViewModel::class)
    abstract fun bindRememberAllViewModel(viewModel: RememberAllViewModel): ViewModel
}