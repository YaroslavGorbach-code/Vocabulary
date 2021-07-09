package yaroslavgorbach.koropapps.vocabulary.data.description.repo

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class RepoDescriptionImp : RepoDescription {
    private val descriptions = listOf(
        Description(ExerciseName.ALPHABET_VERBS, R.string.alphabet_verbs_description),
        Description(ExerciseName.ALPHABET_NOUN, R.string.alphabet_noun_description),
        Description(ExerciseName.ALPHABET_ADJECTIVES, R.string.alphabet_adjectives_description),
        Description(ExerciseName.TAUTOGRAMS, R.string.tautograms_description),
        Description(ExerciseName.NARRATOR_NOUN, R.string.narrator_noun),
        Description(ExerciseName.NARRATOR_ADJECTIVES, R.string.narrator_adjectives),
        Description(ExerciseName.NARRATOR_VERBS, R.string.narrator_verbs),

        Description(ExerciseName.ANTONYMS_AND_SYNONYMS, R.string.antonyms_and_synonyms_description),
        Description(ExerciseName.TEN, R.string.ten_description),
        Description(ExerciseName.ASSOCIATIONS, R.string.associations_description),
        Description(ExerciseName.REMEMBER_ALL, R.string.remember_all_description),
        Description(ExerciseName.LISTS, R.string.lists_description),
    )

    override suspend fun getDescription(exerciseName: ExerciseName): Description {
        return descriptions.find { it.exerciseName == exerciseName }!!
    }
}