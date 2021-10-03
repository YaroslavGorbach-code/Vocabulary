package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.listofcategories.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.listofcategories.ui.ListOfCategoriesFragment

@Subcomponent(modules = [ListOfCategoriesModule::class])
interface ListOfCategoriesComponent {

    fun inject(listOfCategoriesFragment: ListOfCategoriesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): ListOfCategoriesComponent
    }

}