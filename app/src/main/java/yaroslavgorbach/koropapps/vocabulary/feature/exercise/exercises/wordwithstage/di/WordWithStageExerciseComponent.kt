package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.di

import androidx.activity.result.ActivityResultRegistry
import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.di.common.FeatureUtilModule
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.ui.WordWithStageFragment

@Subcomponent(modules = [WordWithStageExerciseModule::class, FeatureUtilModule::class])
interface WordWithStageExerciseComponent {

    fun inject(fragment: WordWithStageFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance exerciseType: ExerciseType,
            @BindsInstance activityResultRegistry: ActivityResultRegistry
        ): WordWithStageExerciseComponent
    }
}
