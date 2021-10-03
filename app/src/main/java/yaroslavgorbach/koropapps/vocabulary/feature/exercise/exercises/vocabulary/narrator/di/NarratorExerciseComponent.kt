package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.narrator.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.narrator.ui.NarratorFragment
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType

@Subcomponent(modules = [NarratorExerciseModule::class])
interface NarratorExerciseComponent {

    fun inject(gameFragment: NarratorFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): NarratorExerciseComponent
    }
}