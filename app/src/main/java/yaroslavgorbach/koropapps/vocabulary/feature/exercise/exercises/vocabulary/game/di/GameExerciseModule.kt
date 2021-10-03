package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.game.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.game.presentation.GameViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class GameExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindGameViewModel(viewModel: GameViewModel): ViewModel
}