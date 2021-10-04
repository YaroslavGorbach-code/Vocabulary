package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.ui.WordWithTimerExerciseFragment

@Subcomponent(modules = [WordWithTimerExerciseModule::class])
interface WordWithTimerExerciseComponent {

    fun inject(fragment: WordWithTimerExerciseFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): WordWithTimerExerciseComponent
    }
}