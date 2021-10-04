package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.word.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.word.ui.WordExerciseFragment

@Subcomponent(modules = [WordExerciseModule::class])
interface WordExerciseComponent {

    fun inject(fragment: WordExerciseFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): WordExerciseComponent
    }
}