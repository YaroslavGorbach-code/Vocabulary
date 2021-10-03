package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.jar.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.jar.presentation.JarViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class JarExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(JarViewModel::class)
    abstract fun bindJarViewModel(viewModel: JarViewModel): ViewModel
}