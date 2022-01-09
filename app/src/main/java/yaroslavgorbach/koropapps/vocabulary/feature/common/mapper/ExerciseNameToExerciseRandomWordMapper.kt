package yaroslavgorbach.koropapps.vocabulary.feature.common.mapper

import android.content.res.Resources
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.ImageUrlsProvider
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.WordCategory
import kotlin.random.Random

class ExerciseNameToExerciseRandomWordMapper(resources: Resources) {

    companion object {
        private const val DEFAULT_RETURN_VALUE = ""
    }

    private val images = ImageUrlsProvider.provide()

    private val nounsAlive = resources.getStringArray(
        WordCategory.NounsAlive().resArray
    ).toList().map(String::lowercase)

    private val nounsNotAlive = resources.getStringArray(
        WordCategory.NounsNotAlive().resArray
    ).toList().map(String::lowercase)

    private val letters = resources.getStringArray(
        WordCategory.Letters().resArray
    ).toList()

    private val problems = resources.getStringArray(
        WordCategory.Problems().resArray
    ).toList()

    private val consonant = letters
        .map(String::lowercase)
        .filter {
            it == "б"
                    || it == "в"
                    || it == "г"
                    || it == "д"
                    || it == "ж"
                    || it == "з"
                    || it == "й"
                    || it == "к"
                    || it == "л"
                    || it == "м"
                    || it == "н"
                    || it == "п"
                    || it == "р"
                    || it == "с"
                    || it == "т"
                    || it == "ф"
                    || it == "х"
                    || it == "ц"
                    || it == "ч"
                    || it == "ш"
                    || it == "щ"
        }

    private val vowels = letters
        .map(String::lowercase)
        .filter {
            it == "а"
                    || it == "и"
                    || it == "о"
                    || it == "у"
                    || it == "ы"
                    || it == "я"
                    || it == "ю"
                    || it == "е"
        }

    private val lettersForRememberAll = letters
        .map(String::lowercase)
        .filterNot {
            it == "е"
                    || it == "ё"
                    || it == "е"
                    || it == "и"
                    || it == "й"
                    || it == "о"
                    || it == "х"
                    || it == "ч"
                    || it == "ш"
                    || it == "ж"
                    || it == "щ"
                    || it == "ъ"
                    || it == "ы"
                    || it == "ь"
                    || it == "э"
                    || it == "ю"
                    || it == "я"
        }

    private val lettersForListOfCategories = letters
        .map(String::lowercase)
        .filterNot {
            it == "е"
                    || it == "ё"
                    || it == "г"
                    || it == "е"
                    || it == "и"
                    || it == "й"
                    || it == "ш"
                    || it == "ж"
                    || it == "щ"
                    || it == "ъ"
                    || it == "ы"
                    || it == "ь"
                    || it == "э"
                    || it == "ю"
                    || it == "я"
        }

    private val categories = resources.getStringArray(
        WordCategory.Category().resArray
    ).toList().map(String::lowercase)

    private val abbreviations = resources.getStringArray(
        WordCategory.Abbreviations().resArray
    ).toList().map(String::lowercase)

    private val terms = resources.getStringArray(
        WordCategory.Terms().resArray
    ).toList().map(String::lowercase)

    private val professions = resources.getStringArray(
        WordCategory.Professions().resArray
    ).toList().map(String::lowercase)

    private val questions = resources.getStringArray(
        WordCategory.Questions().resArray
    ).toList().map(String::lowercase)

    private val fillings = resources.getStringArray(
        WordCategory.Fillings().resArray
    ).toList().map(String::lowercase)

    private val tongueTwistersEasy = resources.getStringArray(
        WordCategory.TongueTwistersEasy().resArray
    ).toList().map(String::lowercase)

    private val tongueTwistersHard = resources.getStringArray(
        WordCategory.TongueTwistersHard().resArray
    ).toList().map(String::lowercase)

    private val tongueTwistersVeryHard = resources.getStringArray(
        WordCategory.TongueTwistersVeryHard().resArray
    ).toList().map(String::lowercase)

    private val soundCombination = resources.getStringArray(
        WordCategory.SoundCombinations().resArray
    ).toList().map(String::lowercase)

    private val difficultWords = resources.getStringArray(
        WordCategory.DifficultWords().resArray
    ).toList().map(String::lowercase)

    fun map(exerciseName: ExerciseName): String {
        return when (exerciseName) {
            ExerciseName.ALPHABET_ADJECTIVES,
            ExerciseName.ALPHABET_NOUN,
            ExerciseName.ALPHABET_VERBS,
            ExerciseName.THREE_LITER_JAR,
            ExerciseName.TAUTOGRAMS -> {
                letters.random()
            }
            ExerciseName.LIST_OF_CATEGORIES -> {
                lettersForListOfCategories.random()
            }
            ExerciseName.REMEMBER_ALL -> {
                lettersForRememberAll.random()
            }
            ExerciseName.THREE_LETTERS -> {
                consonant.random() + consonant.random() + consonant.random()
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
            ExerciseName.ASSOCIATIONS -> {
                nounsNotAlive.random()
            }
            ExerciseName.HALF -> {
                consonant.random() + vowels.random()
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
            ExerciseName.SOUND_COMBINATIONS -> soundCombination.random()
            ExerciseName.DIFFICULT_WORDS -> difficultWords.random()
            ExerciseName.ANTONYMS_AND_SYNONYMS -> fillings.random()
            ExerciseName.COUP_OF_CONSCIOUSNESS -> nounsNotAlive.random()
            ExerciseName.PROBLEM_SOLVING -> problems.random()
            ExerciseName.COUP -> images.random()
        }
    }
}