package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.ui.TautogramsFragment
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

@Subcomponent(modules = [TautogramsExerciseModule::class])
interface TautogramsExerciseComponent {

    fun inject(fragment: TautogramsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): TautogramsExerciseComponent
    }
}