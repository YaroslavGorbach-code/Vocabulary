package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.letters.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.letters.ui.LettersExerciseFragment

@Subcomponent(modules = [LettersExerciseModule::class])
interface LettersExerciseComponent {

    fun inject(fragment: LettersExerciseFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): LettersExerciseComponent
    }
}