package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class RepoExercisesImp : RepoExercises {
    private val exercises: MutableStateFlow<List<Exercise>> = MutableStateFlow(emptyList())

    init {
        exercises.value = listOf(
            Exercise(ExerciseName.ALPHABET_ADJECTIVES, R.drawable.ic_alphabet_a),
            Exercise(ExerciseName.ALPHABET_NOUN, R.drawable.ic_alphabet_n),
            Exercise(ExerciseName.ALPHABET_VERBS, R.drawable.ic_alphabet_v),
            Exercise(ExerciseName.TAUTOGRAMS, R.drawable.ic_promotional_n),
            Exercise(ExerciseName.NARRATOR_NOUN, R.drawable.ic_narrator_n),
            Exercise(ExerciseName.NARRATOR_ADJECTIVES, R.drawable.ic_narrator_a),
            Exercise(ExerciseName.NARRATOR_VERBS, R.drawable.ic_narrator_v),
            Exercise(ExerciseName.ANTONYMS_AND_SYNONYMS, R.drawable.ic_arrows),
            Exercise(ExerciseName.ASSOCIATIONS, R.drawable.ic_assotiations),
            Exercise(ExerciseName.REMEMBER_ALL, R.drawable.ic_test),
            Exercise(ExerciseName.GAME_I_KNOW_5_NAMES, R.drawable.ic_game),
            Exercise(ExerciseName.TEN, R.drawable.ic_ten),
            Exercise(ExerciseName.THREE_LITER_JAR, R.drawable.ic_jar),
        )
    }

    override suspend fun getExercises(): Flow<List<Exercise>> {
        return exercises
    }

    override fun getExercise(exerciseName: ExerciseName): Exercise {
       return exercises.value.find { exercise -> exercise.name == exerciseName }!!
    }
}