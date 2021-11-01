package yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model

import android.graphics.drawable.Drawable
import yaroslavgorbach.koropapps.vocabulary.R

data class Achievement(
    val name: AchievementName,
    private val iconAchieved: Drawable?,
    private val iconNotAchieved: Drawable?,
    var isAchieved: Boolean = false
) {
    val icon: Drawable?
        get() = if (isAchieved) iconAchieved else iconNotAchieved
}

// TODO: 28.10.2021 improve descriptions
enum class AchievementName(val descRes: Int) {
    FIRST_DAILY_TRAINING_COMPLETE(R.string.achievement_first_daily_training),
    FIRST_DICTION_COMPLETE(R.string.achievement_first_diction_exercise),
    FIRST_IMPROVISATION_COMPLETE(R.string.achievement_first_improvisation),
    FIRST_VOCABULARY_COMPLETE(R.string.achievement_first_vocabulary),
    SEVEN_DAILY_TRAININGS_IN_A_ROW_COMPLETE(R.string.achievement_seven_daily_training),
    DICTIONARY_ADJECTIVES_OVER_NORM(R.string.achievement_dictionary_adjectives_over_norm),
    DICTIONARY_NOUNS_OVER_NORM(R.string.achievement_dictionary_nouns_over_norm),
    DICTIONARY_VERBS_OVER_NORM(R.string.achievement_dictionary_verbs_over_norm),
    ALL_TONGUE_TWISTERS_COMPLETE(R.string.achievement_all_tongue_twisters),
    ALL_ALPHABET_EXERCISES_COMPLETE(R.string.achievement_all_alphabet_exercises_complete),
    SPENT_MORE_THEN_HOUR_ON_TRAINING(R.string.achievement_spend_more_then_hour_on_training),
    MORE_THEN_TEN_EXERCISES_COMPLETE(R.string.achievement_more_then_ten_exercises_complete),
    MORE_THEN_FIFTY_EXERCISES_COMPLETE(R.string.achievement_more_then_fifty_exercises_complete),
    MORE_THEN_ONE_HUNDRED_EXERCISES_COMPLETE(R.string.achievement_more_then_one_hundred_exercises_complete),
    MORE_THEN_ONE_THOUSAND_EXERCISES_COMPLETE(R.string.achievement_more_then_one_thousand_exercises_complete),
    FIFTEEN_DAILY_TRAININGS_IN_A_ROW_COMPLETE(R.string.achievement_fifteen_daily_training),
    THIRTY_DAILY_TRAININGS_IN_A_ROW_COMPLETE(R.string.achievement_thirty_daily_training),
}