package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.half.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.half.ui.HalfFragment

@Subcomponent(modules = [HalfExerciseModule::class])
interface HalfExerciseComponent {

    fun inject(fragment: HalfFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): HalfExerciseComponent
    }

}