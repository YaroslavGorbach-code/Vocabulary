package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.wordwithcategory.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.wordwithcategory.ui.WordWithCategoryFragment

@Subcomponent(modules = [WordWithCategoryExerciceModule::class])
interface WordWithCategoryExerciseComponent {

    fun inject(wordWithCategoryFragment: WordWithCategoryFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): WordWithCategoryExerciseComponent
    }

}