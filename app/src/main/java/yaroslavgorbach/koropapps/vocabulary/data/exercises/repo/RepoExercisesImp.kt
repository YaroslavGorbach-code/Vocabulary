package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory

class RepoExercisesImp : RepoExercises {

    private val exercises: Single<List<Exercise>> = Single.just(
        listOf(
            Exercise(ExerciseName.ALPHABET_ADJECTIVES, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.ALPHABET_NOUN, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.ALPHABET_VERBS, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.TAUTOGRAMS, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.NARRATOR_NOUN, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.NARRATOR_ADJECTIVES, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.NARRATOR_VERBS, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.ANTONYMS_AND_SYNONYMS, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.ASSOCIATIONS, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.REMEMBER_ALL, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.GAME_I_KNOW_5_NAMES, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.TEN, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.THREE_LITER_JAR, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.LIST_OF_CATEGORIES, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.THREE_LETTERS, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.HALF, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.SPECIFICATIONS, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.DICTIONARY_NOUN, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.DICTIONARY_VERBS, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.DICTIONARY_ADJECTIVES, ExerciseCategory.VOCABULARY),
            Exercise(ExerciseName.LINGUISTIC_PYRAMIDS, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.RAVEN_LOOK_LIKE_A_TABLE, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.STORYTELLER_IMPROVISER, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.ADVANCED_BINDING, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.WHAT_I_SEE_I_SING_ABOUT, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.OTHER_ABBREVIATIONS, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.MAGIC_NAMING, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.BUYING_SELLING, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.CO_AUTHORED_WITH_DAHL, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.RORSCHACH_TEST, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.WILL_NOT_BE_WORSE, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.QUESTION_ANSWER, ExerciseCategory.COMMUNICATION),
            Exercise(ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS, ExerciseCategory.COMMUNICATION),
        )
    )

    override fun get(): Single<List<Exercise>> {
        return exercises
    }

    override fun get(exerciseName: ExerciseName): Single<Exercise> {
        return exercises
            .map { list ->
                list.first { exercise ->
                    exercise.name == exerciseName
                }
            }
    }
}