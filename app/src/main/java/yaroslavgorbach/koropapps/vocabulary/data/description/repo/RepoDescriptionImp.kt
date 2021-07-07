package yaroslavgorbach.koropapps.vocabulary.data.description.repo

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class RepoDescriptionImp : RepoDescription {
    private val descriptions = listOf(
        Description(ExerciseName.ALPHABET, R.string.alphabet_description),
        Description(ExerciseName.MONOPHONIC, R.string.monophonic_description),
        Description(ExerciseName.NOUN, R.string.noun_description),
        Description(ExerciseName.VERBS, R.string.verbs_description),
        Description(ExerciseName.ANTONYMS_AND_SYNONYMS, R.string.antonyms_and_synonyms_description),
        Description(ExerciseName.TEN, R.string.ten_description),
        Description(ExerciseName.ASSOCIATIONS, R.string.associations_description),
        Description(ExerciseName.REMEMBER_ALL, R.string.remember_all_description),
        Description(ExerciseName.LISTS, R.string.lists_description),
        Description(ExerciseName.ADJECTIVES, R.string.adjectives_description)
    )

    override suspend fun getDescription(exerciseName: ExerciseName): Description {
        return descriptions.find { it.exerciseName == exerciseName }!!
    }
}