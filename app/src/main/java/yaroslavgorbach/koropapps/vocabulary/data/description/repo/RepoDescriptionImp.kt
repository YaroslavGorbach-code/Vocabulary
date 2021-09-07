package yaroslavgorbach.koropapps.vocabulary.data.description.repo
// TODO: 9/7/2021 добавить фичу слово дня
import io.reactivex.rxjava3.core.Single
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
class RepoDescriptionImp : RepoDescription {
    private val descriptions = Single.just(
        listOf(
            Description(ExerciseName.ALPHABET_VERBS),
            Description(ExerciseName.ALPHABET_NOUN),
            Description(ExerciseName.ALPHABET_ADJECTIVES),
            Description(ExerciseName.TAUTOGRAMS),
            Description(ExerciseName.NARRATOR_NOUN),
            Description(ExerciseName.NARRATOR_ADJECTIVES),
            Description(ExerciseName.NARRATOR_VERBS),
            Description(ExerciseName.ANTONYMS_AND_SYNONYMS),
            Description(ExerciseName.TEN),
            Description(ExerciseName.ASSOCIATIONS),
            Description(ExerciseName.REMEMBER_ALL),
            Description(ExerciseName.GAME_I_KNOW_5_NAMES),
            Description(ExerciseName.THREE_LITER_JAR),
            Description(ExerciseName.LIST_OF_CATEGORIES),
            Description(ExerciseName.THREE_LETTERS),
            Description(ExerciseName.HALF),
            Description(ExerciseName.SPECIFICATIONS)
        )
    )

    override fun get(exerciseName: ExerciseName): Single<Description> {
        return descriptions
            .map { list ->
                list.first { it.exerciseName == exerciseName }
            }
    }
}