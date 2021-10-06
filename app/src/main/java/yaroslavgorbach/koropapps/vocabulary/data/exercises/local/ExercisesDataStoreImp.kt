package yaroslavgorbach.koropapps.vocabulary.data.exercises.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

private val Context.exercisesDataStore: DataStore<Preferences> by preferencesDataStore(name = "exercises")

class ExercisesDataStoreImp : ExercisesDataStore {

    companion object {
        private val FAVORITE_EXERCISES_KEY = stringSetPreferencesKey("FAVORITE_EXERCISES_KEY")

    }

    private val exercises = listOf(
        Exercise(ExerciseName.ALPHABET_ADJECTIVES, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.ALPHABET_NOUN, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.ALPHABET_VERBS, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.TAUTOGRAMS, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.NARRATOR_NOUN, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.NARRATOR_ADJECTIVES, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.NARRATOR_VERBS, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.ANTONYMS_AND_SYNONYMS, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.ASSOCIATIONS, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.REMEMBER_ALL, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.GAME_I_KNOW_5_NAMES, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.TEN, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.THREE_LITER_JAR, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.LIST_OF_CATEGORIES, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.THREE_LETTERS, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.HALF, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.SPECIFICATIONS, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.DICTIONARY_NOUN, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.DICTIONARY_VERBS, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.DICTIONARY_ADJECTIVES, ExerciseCategory.VOCABULARY, false),
        Exercise(ExerciseName.LINGUISTIC_PYRAMIDS, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.RAVEN_LOOK_LIKE_A_TABLE, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.STORYTELLER_IMPROVISER, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.ADVANCED_BINDING, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.WHAT_I_SEE_I_SING_ABOUT, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.OTHER_ABBREVIATIONS, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.MAGIC_NAMING, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.BUYING_SELLING, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.CO_AUTHORED_WITH_DAHL, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.RORSCHACH_TEST, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.WILL_NOT_BE_WORSE, ExerciseCategory.COMMUNICATION, false),
        Exercise(ExerciseName.QUESTION_ANSWER, ExerciseCategory.COMMUNICATION, false),
        Exercise(
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS,
            ExerciseCategory.COMMUNICATION,
            false
        ),
    )

    override fun observe(context: Context): Flow<List<Exercise>> {
        return context.exercisesDataStore.data
            .map { prefs ->
                val favoriteExercisesNames = prefs[FAVORITE_EXERCISES_KEY]
                exercises.map { exercise ->
                    if (favoriteExercisesNames?.contains(exercise.name.name) == true) {
                        exercise.isFavorite = true
                    }
                    exercise
                }
            }
    }

    override suspend fun changeFavorite(exercise: Exercise, context: Context) {
        context.exercisesDataStore.edit { prefs ->
            if (exercise.isFavorite) {
                prefs[FAVORITE_EXERCISES_KEY].orEmpty().plus(exercise.name)
            } else {
                prefs[FAVORITE_EXERCISES_KEY].orEmpty().minus(exercise.name)
            }
        }
    }

    override fun get(context: Context, exerciseName: ExerciseName): Flow<Exercise> {
        return observe(context).map { exercises ->
            exercises.first { it.name.name == exerciseName.name }
        }
    }
}