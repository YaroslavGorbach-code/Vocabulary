package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.FeatureUtilModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.presentation.ImageExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.presentation.WordExerciseViewModel

@Module(includes = [ViewModelBuilderModule::class, FeatureUtilModule::class])
abstract class ImageExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(ImageExerciseViewModel::class)
    abstract fun bindViewModel(viewModel: ImageExerciseViewModel): ViewModel
}