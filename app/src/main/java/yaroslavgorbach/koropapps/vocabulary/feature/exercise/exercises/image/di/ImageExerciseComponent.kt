package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.di

import androidx.activity.result.ActivityResultRegistry
import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.ui.ImageExerciseFragment
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.ui.WordExerciseFragment

@Subcomponent(modules = [ImageExerciseModule::class])
interface ImageExerciseComponent {

    fun inject(fragment: ImageExerciseFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance exerciseType: ExerciseType,
            @BindsInstance activityResultRegistry: ActivityResultRegistry
        ): ImageExerciseComponent
    }
}