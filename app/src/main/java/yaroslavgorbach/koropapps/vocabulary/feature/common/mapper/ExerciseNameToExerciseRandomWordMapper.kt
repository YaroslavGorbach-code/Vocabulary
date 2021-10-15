package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import android.content.res.Resources
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.WordCategory
import kotlin.random.Random

class ExerciseNameToExerciseRandomWordMapper(resources: Resources) {

    companion object {
        private const val DEFAULT_RETURN_VALUE = ""
    }

    private val nounsAlive = resources.getStringArray(
        WordCategory.NounsAlive().resArray
    ).toList()

    private val nounsNotAlive = resources.getStringArray(
        WordCategory.NounsNotAlive().resArray
    ).toList()

    private val letters = resources.getStringArray(
        WordCategory.Letters().resArray
    ).toList()

    private val categories = resources.getStringArray(
        WordCategory.Category().resArray
    ).toList()

    private val abbreviations = resources.getStringArray(
        WordCategory.Abbreviations().resArray
    ).toList()

    private val terms = resources.getStringArray(
        WordCategory.Terms().resArray
    ).toList()

    private val professions = resources.getStringArray(
        WordCategory.Terms().resArray
    ).toList()

    private val questions = resources.getStringArray(
        WordCategory.Questions().resArray
    ).toList()

    private val fillings = resources.getStringArray(
        WordCategory.Fillings().resArray
    ).toList()

    private val tongueTwistersEasy = resources.getStringArray(
        WordCategory.TongueTwistersEasy().resArray
    ).toList()

    private val tongueTwistersHard = resources.getStringArray(
        WordCategory.TongueTwistersHard().resArray
    ).toList()

    private val tongueTwistersVeryHard = resources.getStringArray(
        WordCategory.TongueTwistersVeryHard().resArray
    ).toList()

    fun map(exerciseName: ExerciseName): String {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES,
            ExerciseName.ALPHABET_NOUN,
            ExerciseName.ALPHABET_VERBS,
            ExerciseName.REMEMBER_ALL,
            ExerciseName.THREE_LITER_JAR,
            ExerciseName.LIST_OF_CATEGORIES,
            ExerciseName.TAUTOGRAMS -> {
                letters.random()
            }
            ExerciseName.THREE_LETTERS -> {
                letters.random() + letters.random() + letters.random()
            }
            ExerciseName.NARRATOR_NOUN,
            ExerciseName.NARRATOR_ADJECTIVES,
            ExerciseName.NARRATOR_VERBS -> {
                Random.nextInt(3, 15).toString()
            }
            ExerciseName.GAME_I_KNOW_5_NAMES -> {
                categories.random()
            }
            ExerciseName.TEN,
            ExerciseName.SPECIFICATIONS,
            ExerciseName.LINGUISTIC_PYRAMIDS,
            ExerciseName.WHAT_I_SEE_I_SING_ABOUT,
            ExerciseName.MAGIC_NAMING,
            ExerciseName.BUYING_SELLING,
            ExerciseName.RORSCHACH_TEST,
            ExerciseName.ANTONYMS_AND_SYNONYMS,
            ExerciseName.ASSOCIATIONS -> {
                nounsNotAlive.random()
            }
            ExerciseName.HALF -> {
                // TODO: 05.10.2021 доработь этот метод так, чтобы первая буква была не гласная а вторая гласная
                letters.random() + letters.random()
            }

            ExerciseName.DICTIONARY_ADJECTIVES,
            ExerciseName.DICTIONARY_NOUN,
            ExerciseName.DICTIONARY_VERBS -> {
                DEFAULT_RETURN_VALUE
            }
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE -> {
                "${nounsAlive.random()} - ${nounsNotAlive.random()}"
            }
            ExerciseName.STORYTELLER_IMPROVISER -> {
                "${nounsAlive.random()}, ${nounsNotAlive.random()}, ${nounsAlive.random()}, ${nounsNotAlive.random()}"
            }
            ExerciseName.ADVANCED_BINDING -> {
                "${nounsNotAlive.random()}, ${nounsNotAlive.random()}"
            }
            ExerciseName.OTHER_ABBREVIATIONS -> {
                abbreviations.random()
            }
            ExerciseName.CO_AUTHORED_WITH_DAHL -> {
                terms.random()
            }
            ExerciseName.WILL_NOT_BE_WORSE -> {
                professions.random()
            }
            ExerciseName.QUESTION_ANSWER -> {
                questions.random()
            }
            ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS -> {
                "${nounsAlive.random()} - ${fillings.random()}"
            }
            ExerciseName.TONGUE_TWISTERS_EASY -> tongueTwistersEasy.random()
            ExerciseName.TONGUE_TWISTERS_HARD -> tongueTwistersHard.random()
            ExerciseName.TONGUE_TWISTERS_VERY_HARD -> tongueTwistersVeryHard.random()
        }
    }
}