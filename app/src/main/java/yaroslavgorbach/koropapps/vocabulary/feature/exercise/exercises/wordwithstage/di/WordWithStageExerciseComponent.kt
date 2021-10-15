package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.ui.WordWithStageFragment

@Subcomponent(modules = [WordWithStageExerciseModule::class])
interface WordWithStageExerciseComponent {

    fun inject(fragment: WordWithStageFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): WordWithStageExerciseComponent
    }
}
