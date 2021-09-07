package yaroslavgorbach.koropapps.vocabulary.data.exercises.repo

import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class RepoExercisesImp : RepoExercises {
    private val exercises: Single<List<Exercise>> = Single.just(
        listOf(
            Exercise(ExerciseName.ALPHABET_ADJECTIVES),
            Exercise(ExerciseName.ALPHABET_NOUN),
            Exercise(ExerciseName.ALPHABET_VERBS),
            Exercise(ExerciseName.TAUTOGRAMS),
            Exercise(ExerciseName.NARRATOR_NOUN),
            Exercise(ExerciseName.NARRATOR_ADJECTIVES),
            Exercise(ExerciseName.NARRATOR_VERBS),
            Exercise(ExerciseName.ANTONYMS_AND_SYNONYMS),
            Exercise(ExerciseName.ASSOCIATIONS),
            Exercise(ExerciseName.REMEMBER_ALL),
            Exercise(ExerciseName.GAME_I_KNOW_5_NAMES),
            Exercise(ExerciseName.TEN),
            Exercise(ExerciseName.THREE_LITER_JAR),
            Exercise(ExerciseName.LIST_OF_CATEGORIES),
            Exercise(ExerciseName.THREE_LETTERS),
            Exercise(ExerciseName.HALF),
            Exercise(ExerciseName.SPECIFICATIONS)
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