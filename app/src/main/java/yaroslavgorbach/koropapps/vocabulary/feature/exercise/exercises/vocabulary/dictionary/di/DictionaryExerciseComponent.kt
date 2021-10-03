package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.dictionary.di

import dagger.BindsInstance
import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.dictionary.ui.DictionaryFragment

@Subcomponent(modules = [DictionaryExerciseModule::class])
interface DictionaryExerciseComponent {

    fun inject(fragment: DictionaryFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance exerciseType: ExerciseType): DictionaryExerciseComponent
    }
}