package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.threeletters.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.threeletters.ui.ThreeLettersFragment

@Subcomponent(modules = [ThreeLettersExerciseModule::class])
interface ThreeLettersExerciseComponent {

    fun inject(fragment: ThreeLettersFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): ThreeLettersExerciseComponent
    }
}