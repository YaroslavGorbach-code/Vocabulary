package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.game.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.game.ui.GameFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType

@Subcomponent(modules = [GameExerciseModule::class])
interface GameExerciseComponent {
    fun inject(gameFragment: GameFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): GameExerciseComponent
    }
}