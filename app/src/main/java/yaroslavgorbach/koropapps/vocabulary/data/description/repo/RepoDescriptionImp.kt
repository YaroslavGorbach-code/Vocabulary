package yaroslavgorbach.koropapps.vocabulary.data.description.repo

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.exercises.GetExerciseUseCase
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.DescriptionLocal
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class RepoDescriptionImp(getExerciseUseCase: GetExerciseUseCase) : RepoDescription {
    private val descriptions = listOf(
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.ALPHABET_VERBS),
            R.string.alphabet_verbs_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.ALPHABET_NOUN),
            R.string.alphabet_noun_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.ALPHABET_ADJECTIVES),
            R.string.alphabet_adjectives_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.TAUTOGRAMS),
            R.string.tautograms_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.NARRATOR_NOUN),
            R.string.narrator_noun
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.NARRATOR_ADJECTIVES),
            R.string.narrator_adjectives
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.NARRATOR_VERBS),
            R.string.narrator_verbs
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.ANTONYMS_AND_SYNONYMS),
            R.string.antonyms_and_synonyms_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.TEN),
            R.string.ten_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.ASSOCIATIONS),
            R.string.associations_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.REMEMBER_ALL),
            R.string.remember_all_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.GAME_I_KNOW_5_NAMES),
            R.string.game_i_know_5_names_description
        ),
        DescriptionLocal(
            getExerciseUseCase(ExerciseName.THREE_LITER_JAR),
            R.string.three_liter_jar_description
        ),
    )

    override fun getDescription(exerciseName: ExerciseName): DescriptionLocal {
        return descriptions.find { it.exerciseName == exerciseName }!!
    }

}