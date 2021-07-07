package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercises.repo.RepoExercises
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise

class RepoExercisesImp : RepoExercises {
    private val exercises: MutableStateFlow<List<Exercise>> = MutableStateFlow(emptyList())

    init {
        exercises.value = listOf(
            Exercise(1, R.string.alphabet, R.drawable.ic_test),
            Exercise(2, R.string.monophonic, R.drawable.ic_test),
            Exercise(3, R.string.noun, R.drawable.ic_test),
            Exercise(4, R.string.verbs, R.drawable.ic_test),
            Exercise(5, R.string.antonyms_and_synonyms, R.drawable.ic_test),
            Exercise(6, R.string.ten, R.drawable.ic_test),
            Exercise(7, R.string.associations, R.drawable.ic_test),
            Exercise(8, R.string.remember_all, R.drawable.ic_test),
            Exercise(9, R.string.lists, R.drawable.ic_test),
            Exercise(10, R.string.adjectives, R.drawable.ic_test)
            )
    }

    override suspend fun getExercises(): Flow<List<Exercise>> {
        return exercises
    }
}