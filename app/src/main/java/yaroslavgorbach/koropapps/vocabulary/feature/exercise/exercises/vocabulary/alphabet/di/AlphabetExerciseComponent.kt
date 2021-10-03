package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.alphabet.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.alphabet.ui.AlphabetFragment
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

@Subcomponent(modules = [AlphabetExerciseModule::class])
interface AlphabetExerciseComponent {

    fun inject(alphabetFragment: AlphabetFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): AlphabetExerciseComponent
    }
}