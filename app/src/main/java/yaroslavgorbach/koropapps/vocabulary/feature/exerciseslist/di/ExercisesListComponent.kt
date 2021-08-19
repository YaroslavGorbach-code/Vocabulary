package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.di

import dagger.Subcomponent
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.ExercisesListFragment

@Subcomponent(modules = [ExercisesListModule::class])
interface ExercisesListComponent {

    @InternalCoroutinesApi
    fun inject(exercisesListFragment: ExercisesListFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExercisesListComponent
    }
}
