package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.game.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.game.ui.GameFragment
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

@Subcomponent(modules = [GameExerciseModule::class])
interface GameExerciseComponent {
    fun inject(gameFragment: GameFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): GameExerciseComponent
    }
}