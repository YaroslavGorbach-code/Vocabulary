package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithcategory.di

import androidx.activity.result.ActivityResultRegistry
import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.di.common.FeatureUtilModule
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithcategory.ui.WordWithCategoryFragment

@Subcomponent(modules = [WordWithCategoryExerciseModule::class, FeatureUtilModule::class])
interface WordWithCategoryExerciseComponent {

    fun inject(wordWithCategoryFragment: WordWithCategoryFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance exerciseType: ExerciseType,
            @BindsInstance activityResultRegistry: ActivityResultRegistry
        ): WordWithCategoryExerciseComponent
    }
}