package yaroslavgorbach.koropapps.vocabulary.data.exercises.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.utils.parseStringToDate

private val Context.exercisesDataStore: DataStore<Preferences> by preferencesDataStore(name = "exercises")

class ExercisesDataStoreImp : ExercisesDataStore {

    private val favoriteExercisesKeys: Set<Preferences.Key<Boolean>> by lazy {
        val set: MutableSet<Preferences.Key<Boolean>> = mutableSetOf()

        exercises.forEach { set.add(booleanPreferencesKey(it.name.name)) }

        set
    }

    private fun getFavoriteKey(exerciseName: ExerciseName): Preferences.Key<Boolean> {
        return favoriteExercisesKeys.find { it.name == exerciseName.name }!!
    }

    private val exercises = listOf(
        Exercise(ExerciseName.ALPHABET_ADJECTIVES, isFavorite = false),
        Exercise(ExerciseName.ALPHABET_NOUN, isFavorite = false),
        Exercise(ExerciseName.ALPHABET_VERBS, isFavorite = false),
        Exercise(ExerciseName.TAUTOGRAMS, isFavorite = false),
        Exercise(ExerciseName.NARRATOR_NOUN, isFavorite = false),
        Exercise(ExerciseName.NARRATOR_ADJECTIVES, isFavorite = false),
        Exercise(ExerciseName.NARRATOR_VERBS, isFavorite = false),
        Exercise(ExerciseName.ANTONYMS_AND_SYNONYMS, isFavorite = false),
        Exercise(ExerciseName.ASSOCIATIONS, isFavorite = false),
        Exercise(ExerciseName.REMEMBER_ALL, isFavorite = false),
        Exercise(ExerciseName.GAME_I_KNOW_5_NAMES, isFavorite = false),
        Exercise(ExerciseName.TEN, isFavorite = false),
        Exercise(ExerciseName.THREE_LITER_JAR, isFavorite = false),
        Exercise(ExerciseName.LIST_OF_CATEGORIES, isFavorite = false),
        Exercise(ExerciseName.THREE_LETTERS, isFavorite = false),
        Exercise(ExerciseName.HALF, isFavorite = false),
        Exercise(ExerciseName.SPECIFICATIONS, isFavorite = false),
        Exercise(ExerciseName.DICTIONARY_NOUN, isFavorite = false),
        Exercise(ExerciseName.DICTIONARY_VERBS, isFavorite = false),
        Exercise(ExerciseName.DICTIONARY_ADJECTIVES, isFavorite = false),
        Exercise(ExerciseName.LINGUISTIC_PYRAMIDS, isFavorite = false),
        Exercise(ExerciseName.RAVEN_LOOK_LIKE_A_TABLE, isFavorite = false),
        Exercise(ExerciseName.STORYTELLER_IMPROVISER, isFavorite = false),
        Exercise(ExerciseName.ADVANCED_BINDING, isFavorite = false),
        Exercise(ExerciseName.WHAT_I_SEE_I_SING_ABOUT, isFavorite = false),
        Exercise(ExerciseName.OTHER_ABBREVIATIONS, isFavorite = false),
        Exercise(ExerciseName.MAGIC_NAMING, isFavorite = false),
        Exercise(ExerciseName.BUYING_SELLING, isFavorite = false),
        Exercise(ExerciseName.CO_AUTHORED_WITH_DAHL, isFavorite = false),
        Exercise(ExerciseName.RORSCHACH_TEST, isFavorite = false),
        Exercise(ExerciseName.QUESTION_ANSWER, isFavorite = false),
        Exercise(ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS, isFavorite = false),
        Exercise(ExerciseName.WILL_NOT_BE_WORSE, isFavorite = false),
        Exercise(
            ExerciseName.COUP_OF_CONSCIOUSNESS,
            isFavorite = false,
            createDate = parseStringToDate("8-1-2022") // TODO: 1/8/2022 change date on realise
        ),
        Exercise(
            ExerciseName.PROBLEM_SOLVING,
            isFavorite = false,
            createDate = parseStringToDate("8-1-2022")
        ),
        Exercise(ExerciseName.TONGUE_TWISTERS_EASY, isFavorite = false),
        Exercise(ExerciseName.TONGUE_TWISTERS_HARD, isFavorite = false),
        Exercise(ExerciseName.TONGUE_TWISTERS_VERY_HARD, isFavorite = false),
        Exercise(ExerciseName.SOUND_COMBINATIONS, isFavorite = false),
        Exercise(ExerciseName.DIFFICULT_WORDS, isFavorite = false),
    )

    override fun observe(context: Context): Flow<List<Exercise>> {
        return context.exercisesDataStore.data
            .map { prefs ->
                exercises.map { exercise ->
                    val isExerciseFavorite = prefs[getFavoriteKey(exercise.name)]

                    exercise.isFavorite = isExerciseFavorite ?: false

                    exercise
                }
            }
    }

    override suspend fun changeFavorite(exerciseName: ExerciseName, context: Context) {
        context.exercisesDataStore.edit { prefs ->
            prefs[getFavoriteKey(exerciseName)] =
                prefs[getFavoriteKey(exerciseName)]?.not() ?: true
        }
    }

    override fun get(context: Context, exerciseName: ExerciseName): Flow<Exercise> {
        return observe(context).map { exercises ->
            exercises.first { it.name.name == exerciseName.name }
        }
    }
}