package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.di

import androidx.activity.result.ActivityResultRegistry
import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.ui.WordExerciseFragment

@Subcomponent(modules = [WordExerciseModule::class])
interface WordExerciseComponent {

    fun inject(fragment: WordExerciseFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance exerciseType: ExerciseType,
            @BindsInstance activityResultRegistry: ActivityResultRegistry
        ): WordExerciseComponent
    }
}